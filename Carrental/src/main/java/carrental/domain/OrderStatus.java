package carrental.domain;

public enum OrderStatus {
	WAITING_FOR_PHONE_CONFIRMATION,
	WAITING_FOR_PAYMENT,
	PAID,
	USING_A_CAR,
	CAR_RETURNED,
	CAR_BROKEN_WAITING_FOR_PAYMENT,
	DONE,
	CANCELLED,
	CANCELLED_BY_USER
}
