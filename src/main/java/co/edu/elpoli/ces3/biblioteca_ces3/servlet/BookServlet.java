package co.edu.elpoli.ces3.biblioteca_ces3.servlet;

import co.edu.elpoli.ces3.biblioteca_ces3.controller.CtrBook;
import co.edu.elpoli.ces3.biblioteca_ces3.dto.DtoBook;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BookServlet", value = "/Book")
public class BookServlet extends MyServlet{
    private String message;

    private GsonBuilder gsonBuilder;

    private Gson gson;

    private ArrayList<DtoBook> books;

    CtrBook ctr = new CtrBook();

    public void init() {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        books = new ArrayList<>();

        for (int i = 0; i < books.size(); i++)
        {
            System.out.println(books.get(i));
        }
        message = "Hi Lector";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        String bookIdParam = req.getParameter("id");

        if (bookIdParam != null && !bookIdParam.isEmpty()) {
            int bookId = Integer.parseInt(bookIdParam);
            DtoBook book = ctr.getBookById(bookId);
            out.print(gson.toJson(book));
        } else {
            ArrayList<DtoBook> books = ctr.getAllBooks();
            out.print(gson.toJson(books));
        }
        out.flush();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(req);
        DtoBook std = new DtoBook(
                body.get("titulo").getAsString(),
                body.get("autor").getAsString()
        );

        DtoBook newBook = ctr.addBook(std);

        out.print(gson.toJson(newBook));
        out.flush();


    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JsonObject body = gson.fromJson(stringBuilder.toString(), JsonObject.class);
        int bookId = body.get("bookId").getAsInt();

        DtoBook updatedBook = new DtoBook(
                body.get("titulo").getAsString(),
                body.get("autor").getAsString()
        );

        DtoBook result = ctr.updateBook(bookId, updatedBook);

        out.print(gson.toJson(result));
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");

        int bookId = Integer.parseInt(req.getParameter("bookId"));

        ctr.deleteBook(bookId);

        out.print(gson.toJson("Book eliminado"));
        out.flush();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        switch (method){
            case "PATCH":
                this.doPatch(req, resp);
                break;
            default:
                super.service(req, resp);
        }
    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("PATCH Method!");
    }

}
