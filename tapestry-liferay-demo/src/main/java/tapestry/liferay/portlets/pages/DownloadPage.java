package tapestry.liferay.portlets.pages;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.portlet.services.PortletRequestGlobals;
import org.apache.tapestry5.services.Response;

public class DownloadPage {

	@Inject
 	private PortletRequestGlobals globals;
	

	@OnEvent("downloadEvent")
    StreamResponse downloadLink() {
	        return createStreamResponse();
    }
	 
	@OnEvent("serveResource")
    public StreamResponse onServeResource()
    {
 		return createStreamResponse();
    }
	
	private StreamResponse createStreamResponse(){
          
		System.out.println("fromdownload");
        return new StreamResponse()
        {

            public String getContentType()
            {
                return "application/smock";
            }

            public InputStream getStream() throws IOException
            {
                return new ByteArrayInputStream(new String("hellohello").getBytes());
            }

            public void prepareResponse(Response arg0)
            {
            	 arg0.setHeader("Content-Disposition", "attachment; filename=greetings.txt");
                 arg0.setHeader("Expires", "0");
                 arg0.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                 arg0.setHeader("Pragma", "public");
            }

        };
	  }

	 
	 	


}
