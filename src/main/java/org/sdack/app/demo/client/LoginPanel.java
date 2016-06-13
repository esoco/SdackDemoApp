package org.sdack.app.demo.client;

import com.google.gwt.core.client.GWT;

import de.esoco.ewt.build.ContainerBuilder;
import de.esoco.ewt.component.Button;
import de.esoco.ewt.component.TextField;
import de.esoco.ewt.event.EWTEvent;
import de.esoco.ewt.event.EWTEventHandler;
import de.esoco.ewt.event.EventType;
import de.esoco.ewt.style.AlignedPosition;
import de.esoco.ewt.style.StyleData;
import de.esoco.ewt.style.StyleFlag;
import de.esoco.gwt.client.ui.LoginHandler;
import de.esoco.gwt.client.ui.LoginPanelManager;
import de.esoco.gwt.client.ui.PanelManager;

public class LoginPanel extends LoginPanelManager
{

	public LoginPanel(PanelManager<?, ?> rParent, LoginHandler rLoginHandler,
			String sCookiePrefix, boolean bReauthenticate)
	{
		super(rParent, rLoginHandler, sCookiePrefix, bReauthenticate);
	}

	@Override
	protected TextField addPasswortComponents(ContainerBuilder<?> rBuilder)
	{
		TextField rPasswordField = super.addPasswortComponents(rBuilder);

		rBuilder.addLabel(StyleData.DEFAULT, "", null);
		Button aRegisterLink = rBuilder.addButton(
				AlignedPosition.CENTER.setFlags(StyleFlag.HYPERLINK),
				"Registrieren", null);

		aRegisterLink.addEventListener(EventType.ACTION, new EWTEventHandler()
		{
			@Override
			public void handleEvent(EWTEvent rEvent)
			{
				switchToUserRegistration();
			}
		});

		return rPasswordField;
	}

	private void switchToUserRegistration()
	{
		GWT.log("Registration link clicked");
	}

}
