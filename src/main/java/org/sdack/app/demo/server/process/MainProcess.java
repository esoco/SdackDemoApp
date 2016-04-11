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

import de.esoco.process.EntityProcessDefinition;
import de.esoco.process.step.InteractionFragment;

import static de.esoco.process.ProcessRelationTypes.SUB_PROCESS_SEPARATE_CONTEXT;


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

		invoke(WebAppMainView.class);
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
		//~ Static fields/initializers -----------------------------------------

		private static final long serialVersionUID = 1L;

		//~ Methods ------------------------------------------------------------

		/***************************************
		 * {@inheritDoc}
		 */
		@Override
		public void init() throws Exception
		{
			textParam("Welcome").display()
								.value("Welcome to the SDACK demo application.");
		}
	}
}
