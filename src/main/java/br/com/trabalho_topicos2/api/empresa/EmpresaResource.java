package br.com.trabalho_topicos2.api.empresa;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("empresas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class EmpresaResource {
    
    @PersistenceContext(unitName = "TrabalhoTopicosPU")
    private EntityManager entityManager;

    public EmpresaResource() {
    }
    
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") int id) {
        Empresa empresa = findEmpresa(id);        
        if (empresa == null) {
            return empresaNotFoundResponse();
        }
        return Response.ok(empresa).build();
    }
    
    @GET
    public List<Empresa> getAll(@QueryParam("categoriaId") int idCategoria, @QueryParam("destaque") boolean destaque) {
        if (idCategoria > 0) {
            return entityManager
                    .createQuery("SELECT a FROM Empresa a WHERE a.categoria.id = :idCategoria", Empresa.class)
                    .setParameter("idCategoria", idCategoria)
                    .getResultList();
        }
        
        if (destaque) {
            return entityManager
                    .createQuery("SELECT a FROM Empresa a WHERE a.destaque = :destaque", Empresa.class)
                    .setParameter("destaque", destaque)
                    .getResultList();
        }
        return entityManager.createQuery("SELECT a FROM Empresa a", Empresa.class).getResultList();
    }
    
//    @GET
//    public List<Empresa> getAll() {
//        return entityManager.createQuery("SELECT a FROM Empresa a", Empresa.class).getResultList();
//    }

    @POST
    public Response addEmpresa(Empresa empresa) {
        entityManager.persist(empresa);
        return Response.status(Response.Status.CREATED).entity(empresa).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") int id) {
        Empresa empresa = findEmpresa(id);
        if(empresa == null) {
            return empresaNotFoundResponse();
        } 
        entityManager.remove(empresa);
        return Response.noContent().build();
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Empresa empresaAtualizado) {
        Empresa empresa = findEmpresa(id);
        if(empresa == null) {
            return empresaNotFoundResponse();
        }
        empresaAtualizado.setId(id);
        entityManager.merge(empresaAtualizado);
        return Response.ok(empresa).build();
    }
    
    public Empresa findEmpresa(int id) {
        return entityManager.find(Empresa.class, id);
    }
    
    public Response empresaNotFoundResponse() {      
        return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Empresa não encontrada!")
                    .build();
    }
}
