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
package org.sdack.app.demo.client.res;

import de.esoco.gwt.client.app.GeneratedCss;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;


/********************************************************************
 * The resource bundle of the application.
 *
 * @author eso
 */
public interface SdackDemoAppResources extends ClientBundle
{
	//~ Static fields/initializers ---------------------------------------------

	/** The singleton instance of this class. */
	public static final SdackDemoAppResources INSTANCE =
		GWT.create(SdackDemoAppResources.class);

	//~ Methods ----------------------------------------------------------------

	/***************************************
	 * CSS
	 *
	 * @return CSS
	 */
	@Source("main.css")
	MainCss css();

	/***************************************
	 * CSS
	 *
	 * @return CSS
	 */
	@Source("generated/SdackDemoAppCss.css")
	GeneratedCss css_generated();

	/***************************************
	 * Image
	 *
	 * @return Image
	 */
	@Source("img/warning.png")
	ImageResource imWarning();
}
