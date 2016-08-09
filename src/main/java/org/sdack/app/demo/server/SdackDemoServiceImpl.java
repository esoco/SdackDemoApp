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
import de.esoco.data.element.StringDataElement;

import de.esoco.entity.Entity;
import de.esoco.entity.EntityManager;

import de.esoco.gwt.server.GwtApplicationServiceImpl;
import de.esoco.gwt.shared.AuthenticationException;

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

		EntityManager.init(Person.class);

		setApplicationProcess(MainProcess.class);
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected Person authenticate(StringDataElement rLoginData)
		throws AuthenticationException
	{
		Person aUser = new Person();

		aUser.set(Person.LOGIN_NAME, rLoginData.getName());

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
}
