package by.gsu.epamlab.servlets;

import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.utils.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
        maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UploadServlet extends ServletHelper {
    private String fileTemp = "";
    private static final String ID = "id";
    private static final String URL_UPLOAD = "/upload.jsp";
    private static final String FILE_PATH = "D:/upload/";
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String DB_TYPE = "db";
    private static final String URL_MAIN = "/main.jsp";
    private static final String JSP_MAIN = "main.jsp";
    private static final String SEMICOLON = ";";
    private static final String FILENAME = "filename";
    private static final String LEVEL = "=";
    private static final String ID_TASK = "idTask";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ID, req.getParameter(ID));
        forward(URL_UPLOAD, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String savePath = FILE_PATH;
            File fileDir = new File(savePath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }

            req.getParts().forEach(e -> System.out.println(e.getHeader(CONTENT_DISPOSITION)));
            String fileName = "";
            for (Part part : req.getParts()) {
                fileName = extractFileName(part);
                if (!fileName.equals("")) {
                    fileName = new File(fileName).getName();
                    part.write(savePath + File.separator + fileName);
                }
            }
            System.out.println(fileTemp);
            TaskFactory.getTaskDao(DB_TYPE)
                    .setFileURLForTask(TaskFactory.getTaskFromFactory(req.getParameter(ID_TASK),fileTemp ));
            resp.sendRedirect(URL_MAIN);

        } catch (IOException e) {
            e.printStackTrace();
            forwardError(e.getMessage(), JSP_MAIN, req, resp);
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader(CONTENT_DISPOSITION);
        String[] items = contentDisp.split(SEMICOLON);
        for (String s : items) {
            if (s.trim().startsWith(FILENAME)) {
                System.out.println(fileTemp);
                fileTemp = s.substring(s.indexOf(LEVEL) + 2, s.length() - 1);
                return fileTemp;
            }
        }
        return "";
    }
}
