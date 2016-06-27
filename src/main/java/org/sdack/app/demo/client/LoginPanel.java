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

import de.esoco.data.element.StringDataElement;

import de.esoco.ewt.build.ContainerBuilder;
import de.esoco.ewt.component.Button;
import de.esoco.ewt.component.Label;
import de.esoco.ewt.component.TextField;
import de.esoco.ewt.event.EWTEvent;
import de.esoco.ewt.event.EWTEventHandler;
import de.esoco.ewt.event.EventType;
import de.esoco.ewt.style.StyleData;
import de.esoco.ewt.style.StyleFlag;

import de.esoco.gwt.client.ui.LoginHandler;
import de.esoco.gwt.client.ui.LoginPanelManager;
import de.esoco.gwt.client.ui.PanelManager;

import org.sdack.app.demo.shared.SdackDemoService;


/********************************************************************
 * A login panel with an additional user registration option.
 *
 * @author eso
 */
public class LoginPanel extends LoginPanelManager
{
	//~ Instance fields --------------------------------------------------------

	boolean bRegistrationMode;

	private Button rSubmitButton;
	private Button aSwitchModeButton;

	private Label aPasswordVerificationLabel;

	private TextField rPasswordField;
	private TextField aPasswordVerification;

	//~ Constructors -----------------------------------------------------------

	/***************************************
	 * {@inheritDoc}
	 */
	public LoginPanel(PanelManager<?, ?> rParent,
					  LoginHandler		 rLoginHandler,
					  String			 sCookiePrefix,
					  boolean			 bReauthenticate)
	{
		super(rParent, rLoginHandler, sCookiePrefix, bReauthenticate);
	}

	//~ Methods ----------------------------------------------------------------

	/***************************************
	 * Overridden to add the registration components.
	 *
	 * @see LoginPanelManager#addPasswortComponents(ContainerBuilder)
	 */
	@Override
	protected TextField addPasswortComponents(ContainerBuilder<?> rBuilder)
	{
		rPasswordField = super.addPasswortComponents(rBuilder);

		aPasswordVerificationLabel =
			rBuilder.addLabel(StyleData.DEFAULT.setFlags(StyleFlag.HORIZONTAL_ALIGN_RIGHT),
							  "$lblRepeatPassword",
							  null);
		aPasswordVerification	   =
			rBuilder.addTextField(StyleData.DEFAULT.setFlags(StyleFlag.PASSWORD),
								  "");

		// add an empty label to fill a table cell and align the registration
		// link with the input field and submit button
		rBuilder.addLabel(StyleData.DEFAULT, "", null);

		aSwitchModeButton =
			rBuilder.addButton(StyleData.DEFAULT.setFlags(StyleFlag.HYPERLINK),
							   "",
							   null);

		aSwitchModeButton.addEventListener(EventType.ACTION,
			new EWTEventHandler()
			{
				@Override
				public void handleEvent(EWTEvent rEvent)
				{
					setRegistrationMode(!bRegistrationMode);
				}
			});

		setRegistrationMode(false);

		return rPasswordField;
	}

	/***************************************
	 * Overridden to keep a reference to the submit button.
	 *
	 * @see LoginPanelManager#addSubmitLoginComponents(ContainerBuilder)
	 */
	@Override
	protected Button addSubmitLoginComponents(ContainerBuilder<?> rBuilder)
	{
		rSubmitButton = super.addSubmitLoginComponents(rBuilder);

		return rSubmitButton;
	}

	/***************************************
	 * Overridden to add registration information if panel is in registration
	 * mode.
	 *
	 * @see LoginPanelManager#createLoginData(String, String)
	 */
	@Override
	protected StringDataElement createLoginData(
		String sUserName,
		String sPassword)
	{
		StringDataElement rLoginData =
			super.createLoginData(sUserName, sPassword);

		if (bRegistrationMode)
		{
			rLoginData.setFlag(SdackDemoService.REGISTER_NEW_USER);
		}

		return rLoginData;
	}

	/***************************************
	 * Overridden to verify the password if the panel is in registration mode.
	 *
	 * @see LoginPanelManager#login()
	 */
	@Override
	protected void login()
	{
		String sPassword = rPasswordField.getText();

		if (bRegistrationMode &&
			sPassword != null &&
			!sPassword.equals(aPasswordVerification.getText()))
		{
			rPasswordField.addStyleName("error");
			aPasswordVerification.addStyleName("error");
		}
		else
		{
			super.login();
		}
	}

	/***************************************
	 * Switches the panel between login and registration mode.
	 *
	 * @param bRegistration TRUE for registration mode, FALSE for login
	 */
	private void setRegistrationMode(boolean bRegistration)
	{
		bRegistrationMode = bRegistration;

		aPasswordVerificationLabel.setVisible(bRegistration);
		aPasswordVerification.setVisible(bRegistration);

		aSwitchModeButton.setText(bRegistration ? "$btnLoginExistingUser"
												: "$btnRegisterNewUser");

		if (rSubmitButton != null)
		{
			rSubmitButton.setText(bRegistration ? "$btnRegister" : "$btnLogin");
		}
	}
}
