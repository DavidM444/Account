package com.manager.revenuemanager.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionManager {

    //HttpServletResponse no se inicializa ya que es un objeto gestionado por el servidor web: tomcat o Jetty
    /**
     * Mostrar error con su vista y estado http que se configura con HttpServletResponse
     * @param model
     * @param exception
     * @param httpServletResponse
     * @return pagina_error
     */
    @ExceptionHandler(SaldoInsuficienteException.class)
    String saldoInsuficiente(Model model, SaldoInsuficienteException exception,HttpServletResponse httpServletResponse){
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        model.addAttribute("error", exception.getMessage());
        return "errorpage";
    }

    /**
     * Metodo para visualizar error con impl de status response con anotación.
     *
     * @param ex
     * @param model
     * @return view Error
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String manejarExcepcionesGenerales(Exception ex, Model model) {
        model.addAttribute("error", "Ocurrió un error inesperado. Por favor, inténtalo más tarde.");
        return "errorpage";
   }
}
