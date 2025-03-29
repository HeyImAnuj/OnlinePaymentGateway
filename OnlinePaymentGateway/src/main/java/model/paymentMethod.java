package model;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_methods")
public class paymentMethod {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int methodId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private user user;

    public int getMethodId() {
		return methodId;
	}

	public void setMethodId(int methodId) {
		this.methodId = methodId;
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

	public MethodType getMethodType() {
		return methodType;
	}

	public void setMethodType(MethodType methodType) {
		this.methodType = methodType;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Enumerated(EnumType.STRING)
    private MethodType methodType;

    @Column(nullable = false)
    private String details;

    public enum MethodType {
        CREDIT_CARD, DEBIT_CARD, UPI, NET_BANKING
    }
}
