//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// This file is a part of the 'SdackDemoApp' project.
// Copyright 2018 Elmar Sonnenschein, esoco GmbH, Flensburg, Germany
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

import de.esoco.data.element.StringDataElement;

import de.esoco.entity.EntityManager;

import de.esoco.gwt.server.GwtApplicationServiceImpl;
import de.esoco.gwt.shared.AuthenticationException;

import javax.servlet.ServletException;

import org.obrel.core.ProvidesConfiguration;
import org.obrel.core.RelatedObject;
import org.obrel.core.RelationType;

import org.sdack.app.demo.server.entity.Person;
import org.sdack.app.demo.server.process.MainProcess;
import org.sdack.app.demo.shared.SdackDemoService;


/********************************************************************
 * The server-side implementation of the RPC service.
 */
public class SdackDemoServiceImpl extends GwtApplicationServiceImpl<Person>
	implements SdackDemoService, ProvidesConfiguration
{
	//~ Static fields/initializers ---------------------------------------------

	private static final long serialVersionUID = 1L;

	//~ Instance fields --------------------------------------------------------

	private RelatedObject aServiceConfig = new RelatedObject();

	//~ Methods ----------------------------------------------------------------

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	public <T> T getConfigValue(RelationType<T> rType, T rDefaultValue)
	{
		return aServiceConfig.hasRelation(rType) ? aServiceConfig.get(rType)
												 : rDefaultValue;
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	public void init() throws ServletException
	{
		super.init();

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
	protected ProvidesConfiguration getServiceConfiguration()
	{
		return this;
	}
}
