//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// This file is a part of the 'SdackDemoApp' project.
// Copyright 2016 Elmar Sonnenschein, esoco GmbH, Flensburg, Germany
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//	  http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
package org.sdack.app.demo.server;

import de.esoco.data.document.TabularDocumentWriter;
import de.esoco.data.element.DataElementList;
import de.esoco.data.element.StringDataElement;

import de.esoco.entity.Entity;
import de.esoco.entity.EntityManager;
import de.esoco.entity.ExtraAttribute;

import de.esoco.gwt.server.GwtApplicationServiceImpl;
import de.esoco.gwt.shared.AuthenticationException;
import de.esoco.gwt.shared.ServiceException;

import de.esoco.history.HistoryRecord;

import de.esoco.lib.logging.Log;
import de.esoco.lib.security.BCrypt;

import de.esoco.storage.Storage;
import de.esoco.storage.StorageDefinition;
import de.esoco.storage.StorageManager;
import de.esoco.storage.impl.jdbc.JdbcStorageDefinition;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.sdack.app.demo.server.entity.Person;
import org.sdack.app.demo.server.process.MainProcess;
import org.sdack.app.demo.shared.SdackDemoService;


/********************************************************************
 * The server-side implementation of the RPC service.
 */
public class SdackDemoServiceImpl extends GwtApplicationServiceImpl<Person>
	implements SdackDemoService
{
	//~ Static fields/initializers ---------------------------------------------

	private static final long serialVersionUID = 1L;

	//~ Instance fields --------------------------------------------------------

	/**
	 * An entity that holds the global application configuration. This should
	 * typically be read from the database and could use extra attributes for
	 * the configuration values.
	 */
	private Entity aGlobalConfiguration = null;

	//~ Methods ----------------------------------------------------------------

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	public void init(ServletConfig rConfig) throws ServletException
	{
		super.init(rConfig);

		try
		{
			EntityManager.init(Person.class,
							   HistoryRecord.class,
							   ExtraAttribute.class);

			Class.forName("org.h2.Driver");

			StorageDefinition aStorageDef =
				JdbcStorageDefinition.create("jdbc:h2:./test.db");

			StorageManager.setDefaultStorage(aStorageDef);

			Storage rStorage = StorageManager.getStorage(Person.class);

			try
			{
				rStorage.initObjectStorage(Person.class);
				rStorage.initObjectStorage(HistoryRecord.class);
				rStorage.initObjectStorage(ExtraAttribute.class);
			}
			finally
			{
				rStorage.release();
			}
		}
		catch (Exception e)
		{
			Log.fatal("Database initialization failed", e);
			throw new ServletException(e);
		}
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected Person authenticate(StringDataElement rLoginData)
		throws AuthenticationException
	{
		String  sLoginName		 = rLoginData.getName();
		String  sPassword		 = rLoginData.getValue();
		boolean bRegisterNewUser = rLoginData.hasFlag(REGISTER_NEW_USER);

		Person aUser;

		try
		{
			aUser =
				EntityManager.queryEntity(Person.class,
										  Person.LOGIN_NAME,
										  sLoginName,
										  true);

			if (bRegisterNewUser)
			{
				if (aUser != null)
				{
					throw new AuthenticationException("msgLoginNameUnavailable");
				}

				aUser = new Person();

				aUser.set(Person.LOGIN_NAME, sLoginName);
				aUser.set(Person.PASSWORD_HASH,
						  BCrypt.hashpw(sPassword, BCrypt.gensalt()));

				EntityManager.storeEntity(aUser, aUser);
			}
			else
			{
				if (aUser == null)
				{
					throw new AuthenticationException("msgAuthenticationFailed");
				}

				if (!BCrypt.checkpw(sPassword, aUser.get(Person.PASSWORD_HASH)))
				{
					throw new AuthenticationException("msgAuthenticationFailed");
				}
			}
		}
		catch (Exception e)
		{
			throw new AuthenticationException("msgAuthenticationFailed");
		}

		return aUser;
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected TabularDocumentWriter<byte[]> createTableDownloadDocumentWriter()
	{
		throw new UnsupportedOperationException("not implemented");
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected String getApplicationStringPropertiesFile()
	{
		return "data/res/SdackDemoAppStrings.properties";
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected Entity getServiceConfiguration()
	{
		return aGlobalConfiguration;
	}

	/***************************************
	 * Overridden to set the main application process with {@link
	 * #setApplicationProcess(DataElementList, Class)}.
	 *
	 * @see GwtApplicationServiceImpl#initUserData(DataElementList, Entity,
	 *      String)
	 */
	@Override
	protected void initUserData(DataElementList rUserData,
								Person			rUser,
								String			sUserName)
		throws ServiceException
	{
		super.initUserData(rUserData, rUser, sUserName);

		setApplicationProcess(rUserData, MainProcess.class);
	}
}
