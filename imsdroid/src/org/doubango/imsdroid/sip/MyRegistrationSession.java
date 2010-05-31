package org.doubango.imsdroid.sip;

import org.doubango.tinyWRAP.RegistrationSession;
import org.doubango.tinyWRAP.SipSession;

public class MyRegistrationSession extends MySipSession {

	private final RegistrationSession session;
	
	public MyRegistrationSession(MySipStack sipStack) {
		super();
		
		this.session = new RegistrationSession(sipStack);
		
		// commons
		this.init();
		
		/* support for 3GPP SMS over IP */
		this.session.addCaps("+g.3gpp.smsip");
		
		/* support for OMA Large message (as per OMA SIMPLE IM v1) */
		this.session.addCaps("+g.oma.sip-im.large-message");
		
		/* GSMA RCS phase 3 - 3.2 Registration */
		this.session.addCaps("audio");
		this.session.addCaps("+g.3gpp.icsi-ref", "\"urn%3Aurn-7%3A3gpp-service.ims.icsi.mmtel\"");
		this.session.addCaps("+g.3gpp.icsi-ref", "\"urn%3Aurn-7%3A3gpp-application.ims.iari.gsma-vs\"");
		// In addition, in RCS Release 3 the BA Client when used as a primary device will indicate the capability to receive SMS 
		// messages over IMS by registering the SMS over IP feature tag in accordance with [24.341]:
		this.session.addCaps("+g.3gpp.cs-voice");
	}
	
	protected SipSession getSession() {
		return this.session;
	}
	
	public boolean register(){
		return this.session.Register();
	}
	
	public boolean unregister(){
		return this.session.UnRegister();
	}
}
