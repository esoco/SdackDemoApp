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
package org.sdack.app.demo.client;

import de.esoco.gwt.client.app.GwtApplication;
import de.esoco.gwt.shared.GwtApplicationServiceAsync;

import java.util.HashMap;
import java.util.Map;

import org.sdack.app.demo.client.res.SdackDemoAppResources;
import org.sdack.app.demo.client.res.generated.SdackDemoAppStrings;
import org.sdack.app.demo.shared.SdackDemoService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.resources.client.ImageResource;


/********************************************************************
 * The entry point of the application.
 */
public class SdackDemoApp extends GwtApplication
{
	//~ Static fields/initializers ---------------------------------------------

	private static final String COOKIE_PREFIX = "WEBAPP";

	//~ Methods ----------------------------------------------------------------

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected GwtApplicationServiceAsync createApplicationService()
	{
		return GWT.create(SdackDemoService.class);
	}

	/***************************************
	 * Returns the image resource map for this application.
	 *
	 * @return The image resource map
	 */
	@Override
	protected Map<String, ImageResource> getApplicationImages()
	{
		Map<String, ImageResource> aImageResources =
			new HashMap<String, ImageResource>();

		SdackDemoAppResources rResources = SdackDemoAppResources.INSTANCE;

		aImageResources.put("imWarning", rResources.imWarning());

		return aImageResources;
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected ConstantsWithLookup[] getApplicationStrings()
	{
		ConstantsWithLookup aStrings = GWT.create(SdackDemoAppStrings.class);

		return new ConstantsWithLookup[] { aStrings };
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected String getCookiePrefix()
	{
		return COOKIE_PREFIX;
	}

	/***************************************
	 * {@inheritDoc}
	 */
	@Override
	protected void injectApplicationCss()
	{
		super.injectApplicationCss();

		SdackDemoAppResources.INSTANCE.css().ensureInjected();
		SdackDemoAppResources.INSTANCE.css_generated().ensureInjected();
	}
}
