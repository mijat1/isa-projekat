package serverapp.isaBack.DTO.users;

import java.util.List;

public class TokenDTO {
	private String accessToken;
    private List<String> roles;
    private Long expiresIn;
    
	public TokenDTO() {
		
		this.accessToken = null;
        this.expiresIn = null;
	}
	
	public TokenDTO(String accessToken, long expiresIn, List<String> roles) {
		 this.accessToken = accessToken;
	     this.expiresIn = expiresIn;
	     this.roles = roles;
	
	}
	public String getAccessToken() {
        return accessToken;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    public List<String> getRoles() {
        return roles;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
