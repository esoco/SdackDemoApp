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

import java.util.ArrayList;
import java.util.List;

import org.sdack.app.demo.client.res.SdackDemoAppResources;
import org.sdack.app.demo.client.res.generated.SdackDemoAppStrings;
import org.sdack.app.demo.shared.SdackDemoService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.ConstantsWithLookup;


/********************************************************************
 * The entry point of the application.
 */
public class SdackDemoApp extends GwtApplication
{
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
	 * {@inheritDoc}
	 */
	@Override
	protected List<ConstantsWithLookup> getStringResources()
	{
		List<ConstantsWithLookup> aStrings = new ArrayList<>();

		aStrings.add(GWT.create(SdackDemoAppStrings.class));

		return aStrings;
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
