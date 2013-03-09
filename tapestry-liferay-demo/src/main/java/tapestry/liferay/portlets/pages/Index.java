package tapestry.liferay.portlets.pages;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.portlet.Event;
import javax.xml.namespace.QName;
import com.liferay.portlet.EventImpl;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.internal.services.LinkSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.portlet.PortletRenderable;
import org.apache.tapestry5.portlet.services.PortletRequestGlobals;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.upload.services.UploadedFile;


/**
 * Portlet Index
 */
public class Index
{

    @SuppressWarnings("unused")
	@Inject
    @Property
    private Request request;

    @Inject
    private PortletRequestGlobals globals;

    @InjectComponent
    private Zone dateZone;

    @InjectComponent
    private Zone formResultZone;

    @SuppressWarnings("unused")
	@Property
    private String firstName;

    @SuppressWarnings("unused")
	@Property
    @Persist
    private String lastName;

    @Inject
    private LinkSource linkSource;
    
    @Property
    private UploadedFile file;
    
    @Persist
    @Property
    private String filename;

    
    public String getWindowState()
    {
        return globals.getPortletRequest().getWindowState().toString();
    }

    public String getPortletMode()
    {
        return globals.getPortletRequest().getPortletMode().toString();
    }

    public Date getCurrentTime()
    {
        return new Date();
    }

    public PortletRenderable onActionFromToContactPage()
    {
    	PortletRenderable renderable = new PortletRenderable("Contact");
        renderable.addRenderParameter("rp1", "Render Parameter 1");
        renderable.addRenderParameter("rp2", "Render Parameter 2");
    	return renderable;
    }

    public Event onPublishEvent()
    {
        return new EventImpl("sampleEvent", new QName("sampleEvent"), "sampleEvent value");
    }

    public Object onSampleEvent(String event){
        globals.getEventResponse().setRenderParameter("lastEvent", event);
        return this;
    }
    
    public Link onActionFromRefreshWithLink()
    {
        return linkSource.createPageRenderLink("Index", false);
    }

    public Object onActionFromAjaxDateRefresh()
    {
        return dateZone.getBody();
    }

    public String getDateZoneId()
    {
        return dateZone.getClientId();
    }

    public String getFormResultZoneId()
    {
        return formResultZone.getClientId();
    }

    @OnEvent("serveResource")
    public StreamResponse onServeResource()
    {
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

    @Inject 
    private ComponentResources resources; 
    
    public Object onActionFromRefreshAction()
    {
    	//refresh the page
        PortletRenderable renderable = new PortletRenderable(resources.getPageName());
        renderable.addRenderParameter("rp1", "Render Parameter 1");
        renderable.addRenderParameter("rp2", "Render Parameter 2");
        return renderable;
    }

/*    public Object onActionFromReDownloadResource()
    {
        System.out.println("fromredownload");
        return Contact.class;
    }

    @OnEvent(component = "actionException")
    void onActionFromActionException()
    {
        Integer.parseInt("NaN");
    }*/

    Object onSuccessFromFirstNameForm()
    {
        return formResultZone.getBody();
    }
    
    public void onSuccessFromUploadForm() throws IOException
    {
        File copy = File.createTempFile(file.getFileName(), null);
        System.out.println(copy.getPath());
        System.out.println("filename:"+filename);
        file.write(copy);
    }
    
  
}
