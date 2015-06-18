package com.tienda2.jsf;

import com.tienda2.entidades.ProductoCompra;
import com.tienda2.jsf.util.JsfUtil;
import com.tienda2.jsf.util.JsfUtil.PersistAction;
import com.tienda2.bean.ProductoCompraFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "productoCompraController")
@SessionScoped
public class ProductoCompraController implements Serializable {

    @EJB
    private com.tienda2.bean.ProductoCompraFacade ejbFacade;
    private List<ProductoCompra> items = null;
    private ProductoCompra selected;

    public ProductoCompraController() {
    }

    public ProductoCompra getSelected() {
        return selected;
    }

    public void setSelected(ProductoCompra selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getProductoCompraPK().setIdCompra(selected.getCompra().getId());
        selected.getProductoCompraPK().setIdProducto(selected.getProducto().getId());
    }

    protected void initializeEmbeddableKey() {
        selected.setProductoCompraPK(new com.tienda2.entidades.ProductoCompraPK());
    }

    private ProductoCompraFacade getFacade() {
        return ejbFacade;
    }

    public ProductoCompra prepareCreate() {
        selected = new ProductoCompra();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProductoCompraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProductoCompraUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProductoCompraDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ProductoCompra> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<ProductoCompra> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ProductoCompra> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ProductoCompra.class)
    public static class ProductoCompraControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductoCompraController controller = (ProductoCompraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productoCompraController");
            return controller.getFacade().find(getKey(value));
        }

        com.tienda2.entidades.ProductoCompraPK getKey(String value) {
            com.tienda2.entidades.ProductoCompraPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.tienda2.entidades.ProductoCompraPK();
            key.setIdProducto(Integer.parseInt(values[0]));
            key.setIdCompra(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.tienda2.entidades.ProductoCompraPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdProducto());
            sb.append(SEPARATOR);
            sb.append(value.getIdCompra());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProductoCompra) {
                ProductoCompra o = (ProductoCompra) object;
                return getStringKey(o.getProductoCompraPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProductoCompra.class.getName()});
                return null;
            }
        }

    }

}
