//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// This file is a part of the 'BasicWebApp' project.
// Copyright 2015 Elmar Sonnenschein, esoco GmbH, Flensburg, Germany
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
package org.sdack.app.demo.client;

import de.esoco.ewt.build.ContainerBuilder;
import de.esoco.ewt.style.AlignedPosition;

import de.esoco.gwt.client.app.GwtApplicationModule;
import de.esoco.gwt.client.ui.PanelManager;


/********************************************************************
 * The main user interface module of the application.
 *
 * @author eso
 */
public class SdackDemoAppModule extends GwtApplicationModule
{
	//~ Methods ----------------------------------------------------------------

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected void createApplicationPanel(ContainerBuilder<?> rBuilder)
	{
		PanelManager<?, ?> aRootPanel = new SdackDemoAppRootPanel();

		aRootPanel.buildIn(rBuilder, AlignedPosition.CENTER);
	}
}
