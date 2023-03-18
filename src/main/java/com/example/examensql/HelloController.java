package com.example.examensql;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnMostrar;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtArtista;

    @FXML
    private TextField txtAño;

    @FXML
    private TextField txtGenero;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    public void guardar() throws SQLException {
        String sql="INSERT INTO cancion(idCancion,nombre,artista,año,genero)"+
                "VALUES(?,?,?,?,?);";
        ConexionNubeSQL connMySQL=new ConexionNubeSQL();
        Connection conn=connMySQL.open();
        PreparedStatement pstm=conn.prepareStatement(sql);
        pstm.setInt(1,Integer.parseInt(txtId.getText()));
        pstm.setString(2,txtNombre.getText());
        pstm.setString(3,txtArtista.getText());
        pstm.setString(4,txtAño.getText());
        pstm.setString(5,txtGenero.getText());
        pstm.executeUpdate();
        pstm.close();
        connMySQL.close();
        clean();
        lblMensaje.setText("Se guardaron los datos correctamente");
    }
    public void actualizar() throws SQLException{

        String sql="UPDATE cancion" +
                " SET nombre=?, artista=?, año=?, genero=?" +
                " WHERE idCancion=?;";
        ConexionNubeSQL connMySQL=new ConexionNubeSQL();
        Connection conn= connMySQL.open();
        PreparedStatement pstm= conn.prepareStatement(sql);
        pstm.setString(1,txtNombre.getText());
        pstm.setString(2,txtArtista.getText());
        pstm.setString(3,txtAño.getText());
        pstm.setString(4,txtGenero.getText());
        pstm.setInt(5,Integer.parseInt(txtId.getText()));
        pstm.executeUpdate();
        pstm.close();
        connMySQL.close();
        clean();
        lblMensaje.setText("Se actualizaron los datos correctamente");
    }
    public void mostrar() throws SQLException{

        String sql="SELECT * FROM cancion;";
        ConexionNubeSQL connMySQL=new ConexionNubeSQL();
        Connection conn= connMySQL.open();
        PreparedStatement pstm= conn.prepareStatement(sql);
        ResultSet rs= pstm.executeQuery();
        Cancion c=new Cancion();
        while(rs.next()){
            c.setIdCancion(rs.getInt("idCancion"));
            c.setNombre(rs.getString("nombre"));
            c.setArtista(rs.getString("artista"));
            c.setAño(rs.getString("año"));
            c.setGenero(rs.getString("genero"));

            System.out.println("idCancion: "+c.getIdCancion());
            System.out.println("nombre: "+c.getNombre());
            System.out.println("artista: "+c.getArtista());
            System.out.println("año: "+c.getAño());
            System.out.println("genero: "+c.getGenero());
            System.out.println("-------------------------------------------------------");
        }
        rs.close();
        pstm.close();
        connMySQL.close();
        clean();
        lblMensaje.setText("Los datos fueron mostrados en la consola.");
    }
    public void eliminar() throws SQLException{
        String sql="DELETE FROM cancion WHERE idCancion=?;";
        ConexionNubeSQL connMySQL=new ConexionNubeSQL();
        Connection conn=connMySQL.open();
        PreparedStatement pstm= conn.prepareStatement(sql);
        pstm.setInt(1, Integer.parseInt(txtId.getText()));
        pstm.executeUpdate();
        pstm.close();
        connMySQL.close();
        clean();
        lblMensaje.setText("Se elimino con exito.");
    }
    public void clean() {
        txtId.setText("");
        txtNombre.setText("");
        txtArtista.setText("");
        txtAño.setText("");
        txtGenero.setText("");
    }
}