/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import model.Transaksi;
import model.TransaksiDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author victo
 */
@Repository
@Transactional(readOnly = false)
public class TransaksiDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    //hibernate
    @Transactional(readOnly = true)
    public List<Transaksi> findAllHibernate() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Transaksi"); // dari entity bukan dari table
        return query.list();
    }

    public void saveTransaksiHibernate(Transaksi transaksi) {

        sessionFactory.getCurrentSession().saveOrUpdate(transaksi);
    }

    //spring jdbc
    public List<Transaksi> findAll() {
        List<Transaksi> listTransaksi = jdbcTemplate.query("SELECT Id as id, Waktu as waktu FROM tb_Transaksi",
                new BeanPropertyRowMapper(Transaksi.class));
        return listTransaksi;
    }

    public List<Map<String, Object>> findAllJoin(int id) {
        return jdbcTemplate.queryForList("SELECT t.id as idTransaksi, t.Waktu, td.id as idTransaksiDetail, td.Item, td.Price, td.Qty FROM tb_Transaksi t JOIN TransaksiDetail td ON t.Id = td.IdTransaksi WHERE t.Id = ?", id);

    }

    public int addTransaksi(Timestamp waktu) {
        String sql = "INSERT INTO tb_Transaksi VALUES(:waktu)";
        Map<String, Timestamp> paramMap = new HashMap<String, Timestamp>();
        paramMap.put("waktu", waktu);
        return namedJdbcTemplate.update(sql, paramMap);
    }

    public int updateTransaksi(Transaksi transaksi) {
        String sql = "UPDATE tb_Transaksi SET Waktu = :waktu where Id = :id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("waktu", transaksi.getWaktu());
        paramMap.put("id", transaksi.getId());
        return namedJdbcTemplate.update(sql, paramMap);
    }

    public int deleteTransaksi(Transaksi transaksi) {
        String sql = "DELETE tb_Transaksi WHERE Id=:id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", transaksi.getId());
        return namedJdbcTemplate.update(sql, paramMap);
    }

    public Transaksi findById(int id) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        return (Transaksi) namedJdbcTemplate
                .queryForObject("SELECT Id as id, Waktu as waktu FROM tb_Transaksi where Id = :id ",
                        param, new BeanPropertyRowMapper(Transaksi.class));
    }

}
