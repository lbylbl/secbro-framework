package org.secbroframework.remote;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthSSLX509TrustManager implements X509TrustManager {
    private X509TrustManager defaultTrustManager = null;

    /** Log object for this class. */
    private static final Logger logger = LoggerFactory.getLogger(AuthSSLX509TrustManager.class);

    /**
     * Constructor for AuthSSLX509TrustManager.
     */
    public AuthSSLX509TrustManager(final X509TrustManager defaultTrustManager) {
        super();
        if (defaultTrustManager == null) {
            throw new IllegalArgumentException("Trust manager may not be null");
        }
        this.defaultTrustManager = defaultTrustManager;
    }

    /**
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
	@Override
    public X509Certificate[] getAcceptedIssuers() {
        return this.defaultTrustManager.getAcceptedIssuers();
    }

	@Override
	public void checkClientTrusted(X509Certificate[] certificates, String authType)
			throws CertificateException {
		
		if (logger.isDebugEnabled() && certificates != null && certificates.length >0) {
            for (int c = 0; c < certificates.length; c++) {
                X509Certificate cert = certificates[c];
                logger.info(" Client certificate {} :",c + 1);
                logger.info("  Subject DN: {}",cert.getSubjectDN());
                logger.info("  Subject DN: {}",cert.getSubjectDN());
                logger.info("  Signature Algorithm: {}",cert.getSigAlgName());
                logger.info("  Valid from: {}",cert.getNotBefore());
                logger.info("  Valid until: {}",cert.getNotAfter());
                logger.info("  Issuer: {}",cert.getIssuerDN());
            }
        }
		this.defaultTrustManager.checkClientTrusted(certificates, authType);
		
	}

	@Override
	public void checkServerTrusted(X509Certificate[] certificates, String authType)
			throws CertificateException {
		if (logger.isDebugEnabled() && certificates != null && certificates.length > 0) {
            for (int c = 0; c < certificates.length; c++) {
                X509Certificate cert = certificates[c];
                logger.info(" Server certificate {}:",(c + 1));
                logger.info("  Subject DN: {}",cert.getSubjectDN());
                logger.info("  Signature Algorithm: {}",cert.getSigAlgName());
                logger.info("  Valid from: {}",cert.getNotBefore());
                logger.info("  Valid until: {}",cert.getNotAfter());
                logger.info("  Issuer: {}",cert.getIssuerDN());
            }
        }
		this.defaultTrustManager.checkServerTrusted(certificates, authType);
	}
}
