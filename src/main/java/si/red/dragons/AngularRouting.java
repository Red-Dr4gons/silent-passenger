package si.red.dragons;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AngularRouting extends HttpFilter {
    private static final long serialVersionUID = 6658181742256174754L;

    private String[] paths = {
            "(^\\/dashboard(\\/)?$)",
            "(^\\/profile[\\/]?$)",
            "(^\\/rides[\\/]?$)"

    };

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        for (String pattern : paths) {
            if (request.getRequestURI().matches(pattern)) {
                request.getRequestDispatcher("/index.html").forward(request, response);
            }
        }

        chain.doFilter(request, response);
    }
}
