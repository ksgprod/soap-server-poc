package br.com.ksgprod.domain;

public enum Status {
	
	SUCCESS(br.com.ksgprod.soap.wsdl.Status.SUCCESS),
	FAILURE(br.com.ksgprod.soap.wsdl.Status.FAILURE);
	
	private br.com.ksgprod.soap.wsdl.Status externalStatus;

	public br.com.ksgprod.soap.wsdl.Status getExternalStatus() {
		return externalStatus;
	}

	private Status(br.com.ksgprod.soap.wsdl.Status externalStatus) {
		this.externalStatus = externalStatus;
	}
	
}
