/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Pers;
import com.sv.udb.modelo.tipo_pers;
import com.sv.udb.modelo.ubic_geof;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Blob;
/**
 *
 * @author Owner
 */
public class PersCtrl {
    public List<Pers> consTodo()
    {
        List<Pers> resp = new ArrayList<>();
        Connection cn = new Conexion().getConn();
        try
        {
            String consulta = "SELECT * FROM pers inner join tipo_pers on tipo_pers.CODI_TIPO_PERS = pers.CODI_TIPO_PERS inner join ubic_geog on pers.CODI_UBIC_GEOG = ubic_geog.CODI_UBIC_GEOG";
            PreparedStatement cmd = cn.prepareStatement(consulta);
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Pers(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBlob(4), new tipo_pers(rs.getInt(5), rs.getString(16), rs.getString(17), rs.getString(18), rs.getBlob(19)), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), new ubic_geof(rs.getInt(20), rs.getString(21), rs.getInt(22), rs.getString(23), rs.getString(24), rs.getBlob(25)), rs.getString(12), rs.getString(13), rs.getInt(14)));
                System.out.println(resp);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(cn != null)
            {
                try
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        return resp;
    }
    
    public boolean guar(Pers obje)
    {
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            String consulta = "INSERT INTO pers VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, 1)";
            PreparedStatement cmd = cn.prepareStatement(consulta);
            cmd.setString(1, obje.getNomb_pers());
            cmd.setString(2, obje.getApel_pers());
            cmd.setInt(3, obje.getCodi_tipo_pers().getCodi_tipo_pers());
            cmd.setBlob(4, obje.getFoto_pers());
            cmd.setString(5, obje.getGene_pers());
            cmd.setString(6, obje.getFech_naci_pers());
            cmd.setString(7, obje.getDui_pers());
            cmd.setString(8, obje.getNit_pers());
            cmd.setString(9, obje.getTipo_sang_pers());
            cmd.setInt(10, obje.getCodi_ubic_geof().getCodi_ubic_geof());
            cmd.setString(11, obje.getFech_alta());
            cmd.executeUpdate();
            resp = true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(cn != null)
            {
                try
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        return resp;
    }
    
}
