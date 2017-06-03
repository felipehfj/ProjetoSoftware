package br.uff.ps.controle;

import br.uff.ps.logicadominio.Admin;
import br.uff.ps.logicadominio.Expression;
import br.uff.ps.servicostecnicos.AdminRepository;
import br.uff.ps.servicostecnicos.ExpressionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public abstract class AbstractController {


    @Autowired protected AdminRepository<Admin> adminRepository;
    @Autowired protected ExpressionRepository<Expression> expressionRepository;


    protected boolean isUserValid(HttpServletRequest request) {
        try {
            String encodedAuth = request.getHeaders("Authorization").nextElement().split(" ")[1];
            String decodedAuth = new String(Base64.getDecoder().decode(encodedAuth));
            if (isNotBlank(decodedAuth)) {
                String[] credentials = decodedAuth.split(":");
                String username = credentials[0];
                String password = credentials[1];

                Admin admin = adminRepository.findByUserName(username);

                return admin != null && admin.getPassword().equals(password);
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }
}
