package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class transaction {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int transactionId;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private user user;

	    @ManyToOne
	    @JoinColumn(name = "merchant_id", nullable = false)
	    private merchant merchant;

	    public int getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(int transactionId) {
			this.transactionId = transactionId;
		}

		public user getUser() {
			return user;
		}

		public void setUser(user user) {
			this.user = user;
		}

		public merchant getMerchant() {
			return merchant;
		}

		public void setMerchant(merchant merchant) {
			this.merchant = merchant;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		private BigDecimal amount;

	    @Enumerated(EnumType.STRING)
	    private Status status;

	    @Column(nullable = false, updatable = false)
	    private LocalDateTime createdAt = LocalDateTime.now();

	    public enum Status {
	        PENDING, SUCCESS, FAILED
	    }
}
