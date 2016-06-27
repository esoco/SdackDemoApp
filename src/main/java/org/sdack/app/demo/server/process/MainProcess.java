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
package org.sdack.app.demo.server.process;

import static de.esoco.process.ProcessRelationTypes.SUB_PROCESS_SEPARATE_CONTEXT;

import org.sdack.app.demo.server.entity.Person;

import de.esoco.entity.EntityRelationTypes;
import de.esoco.lib.property.Layout;
import de.esoco.process.EntityProcessDefinition;
import de.esoco.process.ProcessRelationTypes;
import de.esoco.process.step.InteractionFragment;


/********************************************************************
 * The mainprocess of the application.
 *
 * @author eso
 */
public class MainProcess extends EntityProcessDefinition
{
	//~ Static fields/initializers ---------------------------------------------

	private static final long serialVersionUID = 1L;

	//~ Constructors -----------------------------------------------------------

	/***************************************
	 * Creates a new instance.
	 */
	@SuppressWarnings("boxing")
	public MainProcess()
	{
		// always invoke sub-processes in their own context to prevent
		// parameter conflicts on repeated executions
		setParameter(SUB_PROCESS_SEPARATE_CONTEXT, true);

		invoke(WebAppMainView.class).set(ProcessRelationTypes.FINAL_STEP);
	}

	//~ Inner Classes ----------------------------------------------------------

	/********************************************************************
	 * The interaction fragment that displays the main view of the customer
	 * self-care application.
	 *
	 * @author eso
	 */
	public static class WebAppMainView extends InteractionFragment
	{
		//~ Enums --------------------------------------------------------------

		private static final long serialVersionUID = 1L;

		//~ Static fields/initializers -----------------------------------------

		/***************************************
		 * {@inheritDoc}
		 */
		@Override
		public void init() throws Exception
		{
			layout(Layout.GRID);
			buttons(MenuAction.class).onAction(this::handleMenuAction);

			label("Welcome to the SDACK demo application.");

			entityParam(Person.class).query(null)
									 .attributes(EntityRelationTypes.ENTITY_ID,
												 Person.LOGIN_NAME)
									 .input().hideLabel();
		}

		//~ Methods ------------------------------------------------------------

		/***************************************
		 * Handles menu events.
		 *
		 * @param eAction The menu action that occurred
		 */
		private void handleMenuAction(MenuAction eAction)
		{
			if (eAction == MenuAction.LOGOUT)
			{
				getProcess().logoutProcessUser();
			}
		}

		/********************************************************************
		 * Enumeration of the menu actions in this view.
		 */
		enum MenuAction { LOGOUT }
	}
}
