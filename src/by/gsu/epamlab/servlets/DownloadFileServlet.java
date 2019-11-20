package by.gsu.epamlab.servlets;

import by.gsu.epamlab.utils.ServletHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends ServletHelper {
    private static final String FILE_PATH = "D:/upload/";
    private static final String URL = "url";
    private static final String MIME_TYPE = "application/octet-stream";
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String HEADER_VALUE = "attachment; filename=\"%s\"";
    private static final int MINUS_1 = -1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String filePath = FILE_PATH + req.getParameter(URL);
        File downloadFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream((downloadFile));

        ServletContext context = getServletContext();

        String mimeType = context.getMimeType(filePath);
        if(mimeType == null){
            mimeType = MIME_TYPE;
        }

        resp.setContentType(mimeType);
        resp.setContentLength((int) downloadFile.length());

        String headerKey = CONTENT_DISPOSITION;
        String headerValue = String.format(HEADER_VALUE, downloadFile.getName());
        resp.setHeader(headerKey,headerValue);

        OutputStream outputStream = resp.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = MINUS_1;

        while((bytesRead = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();
    }
}
