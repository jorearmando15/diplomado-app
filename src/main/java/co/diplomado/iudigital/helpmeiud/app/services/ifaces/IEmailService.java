package co.diplomado.iudigital.helpmeiud.app.services.ifaces;

public interface IEmailService {

    boolean sendMail(String mensaje, String email, String asunto);
}
