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
package org.sdack.app.demo.client;

import de.esoco.data.element.DataElementList;

import de.esoco.ewt.build.ContainerBuilder;
import de.esoco.ewt.component.Panel;
import de.esoco.ewt.layout.DockLayout;
import de.esoco.ewt.style.AlignedPosition;
import de.esoco.ewt.style.StyleData;

import de.esoco.gwt.client.app.ProcessPanelManager;
import de.esoco.gwt.shared.ProcessState;


/********************************************************************
 * The root panel manager for the demo web application.
 *
 * @author eso
 */
public class SdackDemoAppRootPanel
	extends SdackDemoAppPanelManager<Panel, SdackDemoAppPanelManager<?, ?>>
{
	//~ Static fields/initializers ---------------------------------------------

	private static final StyleData MAIN_PANEL_STYLE =
		addStyles(AlignedPosition.CENTER, SdackDemoApp.css().sdaMainPanel());

	//~ Instance fields --------------------------------------------------------

	private DataElementList     rUserData;
	private ProcessPanelManager aProcessPanelManager;

	//~ Constructors -----------------------------------------------------------

	/***************************************
	 * Creates a new instance.
	 */
	public SdackDemoAppRootPanel()
	{
		super(null, SdackDemoApp.css().sdaRootPanel());

		setLoginMode(LoginMode.PAGE);
	}

	//~ Methods ----------------------------------------------------------------

	/***************************************
	 * Root implementation that displays the message in the top panel.
	 *
	 * @see CustomerSelfCarePanelManager#displayMessage(String)
	 */
	@Override
	public void displayMessage(String sMessage, int nDisplayTime)
	{
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	public void dispose()
	{
		rUserData = null;
		removeApplicationPanel();

		super.dispose();
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected void addComponents()
	{
		// initiate authentication; on success the method userAuthenticated()
		// will be invoked
		checkAuthentication();
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected ContainerBuilder<Panel> createContainer(
		ContainerBuilder<?> rBuilder,
		StyleData			rStyleData)
	{
		return rBuilder.addPanel(rStyleData, new DockLayout(false, true));
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected void displayProcess(ProcessState rProcessState)
	{
		if (rProcessState != null && !rProcessState.isFinished())
		{
			aProcessPanelManager =
				new ProcessPanelManager(this,
										rProcessState.getName(),
										false,
										false);

			build(aProcessPanelManager, MAIN_PANEL_STYLE);

			aProcessPanelManager.handleCommandResult(rProcessState);
		}
		else
		{
			processFinished(null, rProcessState);
		}
	}

	/***************************************
	 * Returns the client-specific data for the currently authenticated user.
	 *
	 * @see CustomerSelfCarePanelManager#getUserData()
	 */
	@Override
	protected DataElementList getUserData()
	{
		return rUserData;
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected void logout()
	{
		dispose();
		checkAuthentication();
	}

	/***************************************
	 * Removes the main panel if it exists.
	 */
	@Override
	protected void removeApplicationPanel()
	{
		if (aProcessPanelManager != null)
		{
			removeComponent(aProcessPanelManager.getContainer());
			aProcessPanelManager = null;
		}
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected void userAuthenticated(DataElementList rUserData)
	{
		this.rUserData = rUserData;

		removeApplicationPanel();

		executeApplicationProcess(rUserData);
	}
}
