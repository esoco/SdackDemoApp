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
package org.sdack.app.demo.shared;

import de.esoco.gwt.shared.GwtApplicationService;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/********************************************************************
 * The GWT RPC service of this application.
 */
@RemoteServiceRelativePath(SdackDemoService.SERVICE_URL)
public interface SdackDemoService extends GwtApplicationService
{
	//~ Static fields/initializers ---------------------------------------------

	/** The URL to the service */
	public static final String SERVICE_URL = "srv";

	/** The name of the application process. */
	public static final String APP_PROCESS =
		"org.sdack.app.demo.server.process.MainProcess";
}
