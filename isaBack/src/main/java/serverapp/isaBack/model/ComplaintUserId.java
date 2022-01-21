package serverapp.isaBack.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

	@Embeddable
	public class ComplaintUserId implements Serializable{

		private static final long serialVersionUID = 1L;

		@ManyToOne(optional = false)
		private User user;
		
		@ManyToOne(optional = false)
		private Client client;
		
		
	    public ComplaintUserId() {}
		
		public ComplaintUserId(User user, Client client) {
			super();
			this.user = user;
			this.client = client;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}



}

