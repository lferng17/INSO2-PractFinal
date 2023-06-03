package inso2023.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import inso2023.ejb.ArbitroFacadeLocal;
import inso2023.ejb.PartidoFacadeLocal;
import inso2023.model.Arbitro;
import inso2023.model.Partido;

@ManagedBean
@ViewScoped
public class VistaEliminarArbitroController implements Serializable{

    private int idArbitro;
    private List<Arbitro> listaArbitros;

    @EJB
    private ArbitroFacadeLocal arbitroEJB;
    @EJB
    private PartidoFacadeLocal partidoEJB;

    @PostConstruct
    public void init(){
        listaArbitros = arbitroEJB.findAll();
    }

    public void verificarAdministrador() throws Exception{
        String usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(!usuario.equals("admin")){
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }

    public void eliminarArbitro() throws IOException{
        try{
            Arbitro arbitro = arbitroEJB.find(idArbitro);
            List<Partido> partidos = partidoEJB.findPartidoByArbitro(arbitro);
            if (!partidos.isEmpty()){
                for(Partido partido : partidos){
                    System.out.println(partido.getIdPartido());
                    partidoEJB.remove(partido);
                }
            }
            arbitroEJB.remove(arbitro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arbitro eliminado", "Arbitro eliminado con éxito!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Arbitro no eliminado", "No se ha podido eliminar el arbitro, compruebe los datos introducidos."));
        }   

    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    public List<Arbitro> getListaArbitros() {
        return listaArbitros;
    }

    public void setListaArbitros(List<Arbitro> listaArbitros) {
        this.listaArbitros = listaArbitros;
    }

}

