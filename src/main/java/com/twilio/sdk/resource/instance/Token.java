package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;

import java.util.*;

public class Token extends InstanceResource {

	/**
	 * Instantiates a new token.
	 *
	 * @param client the client
	 */
	public Token(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new token.
	 *
	 * @param client the client
	 * @param username the username
	 */
	public Token(TwilioRestClient client, String username) {
		super(client);
		if (username == null) {
			throw new IllegalStateException("The username for a Token can not be null");
		}
		this.setProperty("username", username);
	}

	/**
	 * Instantiates a new token.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Token(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/*
	 * Property getters
	 */

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return this.getProperty(SID_PROPERTY);
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseDate(this.getProperty("date_created"));
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseDate(this.getProperty("date_updated"));
	}

	/**
	 * Gets the account sid.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return this.getProperty("account_sid");
	}

	/**
	 * Gets the username
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.getProperty("username");
	}

	/**
	 * Gets the password
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.getProperty("password");
	}

	/**
	 * Gets the ttl
	 *
	 * @return the ttl
	 */
	public Integer getTtl() {
		return Integer.parseInt(this.getProperty("ttl"));
	}

	class IceServer {
		public final String url;
		public final String username;
		public final String credential;

		public IceServer(Map<String, String> params) {
            String server_url = null;
            String server_username = null;
            String server_credential = null;
            if (params.containsKey("url")) {
                server_url = params.get("url");
            }
            if (params.containsKey("username")) {
                server_username = params.get("username");
            }
            if (params.containsKey("credential")) {
                server_credential = params.get("credential");
            }
			this.url = server_url;
			this.username = server_username;
			this.credential = server_credential;
		}

        public boolean hasUsername() {
            return this.url != null;
        }

        public boolean hasCredential() {
            return this.url != null;
        }

		public String getUrl() {
			return this.url;
		}

		public String getUsername() {
			return this.username;
		}

		public String getCredential() {
			return this.credential;
		}
	}

	public List<IceServer> getIceServers() {

        List<IceServer> iceServers = new ArrayList<IceServer>();
		for (Map<String, String> server : (List<HashMap<String, String>>) this.getObject("ice_servers")) {
			IceServer token = new IceServer(server);
            iceServers.add(token);
		}
        return iceServers;
	}

}
