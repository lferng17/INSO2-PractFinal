package inso2023.controller;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import inso2023.model.Equipo;
import inso2023.ejb.EquipoFacadeLocal;
import javax.ejb.EJB;

@Named
@ViewScoped
public class ClasificacionEquiposController implements Serializable{

    @EJB
    private EquipoFacadeLocal equipoFacade;
    
    
    


}