package Proyecto.logic;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Service {
    private static Service theInstance;
    public static Service instance(){
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }
    Socket s;
    ObjectOutputStream os;
    ObjectInputStream is;

    String sid; // Session Id

    public Service() {
        try {
            s = new Socket(Protocol.SERVER, Protocol.PORT);
            os = new ObjectOutputStream(s.getOutputStream());
            is = new ObjectInputStream(s.getInputStream());

            os.writeInt(Protocol.SYNC);
            os.flush();
            sid=(String)is.readObject(); // Stores returned Session Id

        } catch (Exception e) { System.exit(-1);}
    }

    public String getSid() {
        return sid;
    }

    public void create(Producto e) throws Exception {
        os.writeInt(Protocol.PRODUCTO_CREATE);
        os.writeObject(e);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("PRODUCTO DUPLICADO");
    }

    public Producto read(Producto e) throws Exception {
        os.writeInt(Protocol.PRODUCTO_READ);
        os.writeObject(e);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) return (Producto) is.readObject();
        else throw new Exception("PRODUCTO NO EXISTE");
    }

    public void update(Producto e) throws Exception {
        os.writeInt(Protocol.PRODUCTO_UPDATE);
        os.writeObject(e);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("PRODUCTO NO EXISTE");
    }

    public void delete(Producto e) throws Exception {
        os.writeInt(Protocol.PRODUCTO_DELETE);
        os.writeObject(e);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("PRODUCTO NO EXISTE");
    }

    public List<Producto> search(Producto e) {
        try {
            os.writeInt(Protocol.PRODUCTO_SEARCH);
            os.writeObject(e);
            os.flush();
            if (is.readInt() == Protocol.ERROR_NO_ERROR) {
                return (List<Producto>) is.readObject();
            }
            else return List.of();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Categoria> search(Categoria e) {
        try {
            os.writeInt(Protocol.CATEGORIA_SEARCH);
            os.writeObject(e);
            os.flush();
            if (is.readInt() == Protocol.ERROR_NO_ERROR) {
                return (List<Categoria>) is.readObject();
            }
            else return List.of();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void disconnect() throws Exception {
        os.writeInt(Protocol.DISCONNECT);
        os.flush();
        s.shutdownOutput();
        s.close();
    }

    public void stop() {
        try {
            disconnect();
        } catch (Exception e) {
            System.exit(-1);
        }
    }
 }
