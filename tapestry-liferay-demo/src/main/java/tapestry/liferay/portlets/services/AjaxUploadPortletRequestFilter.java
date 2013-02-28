package tapestry.liferay.portlets.services;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.tapestry5.portlet.services.PortletResourceRequestFilter;
import org.apache.tapestry5.portlet.services.PortletResourceRequestHandler;
import org.got5.tapestry5.jquery.services.AjaxUploadDecoder;

import com.liferay.portal.util.PortalUtil;

/**
 * Filtre tapestry-portlet utilise pour les ResourceRequest sur les portlets.
 * Permet de recuper le flux HTTP contenant le fichier dans le cas de l'upload de fichier
 * (AJAX/HTML5) et de le transmettre au decodeur. Utilisation de l'api liferay (PortalUtil) 
 * pour obtenir l'objet HttpServletRequest a partir d'une ResourceRequest. 
 */
public class AjaxUploadPortletRequestFilter implements PortletResourceRequestFilter {

    private AjaxUploadDecoder decoder;

    /**
     * Constructeur par default.
     * @param decoder du flux contenant le fichier.
     */
    public AjaxUploadPortletRequestFilter(AjaxUploadDecoder decoder) {
    	this.decoder = decoder;
    }

	/**
	 * {@inheritDoc}
	 */
	public boolean service(ResourceRequest request, ResourceResponse response,
			PortletResourceRequestHandler handler) throws IOException,
			PortletException {
        
		if (decoder.isAjaxUploadRequest(PortalUtil.getHttpServletRequest(request))) {
            decoder.setupUploadedFile(PortalUtil.getHttpServletRequest(request));
        }
        return handler.service(request, response);
	}
	
	
}
