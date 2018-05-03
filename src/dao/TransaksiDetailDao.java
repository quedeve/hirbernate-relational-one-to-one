package dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import model.TransaksiDetail;

@Repository
public class TransaksiDetailDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;
    
    public List<TransaksiDetail> findAll() {
        List<TransaksiDetail> listTransaksiDetail = jdbcTemplate.query("SELECT Id as id, IdTransaksi as idTransaksi, Item as item, Qty as qty, Price as price FROM TransaksiDetail",
                new BeanPropertyRowMapper(TransaksiDetail.class));
        return listTransaksiDetail;
    }
     public TransaksiDetail findById(int id) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        return (TransaksiDetail) namedJdbcTemplate
                .queryForObject("SELECT Id as id, IdTransaksi as idTransaksi, Item as item, Qty as qty, Price as price FROM TransaksiDetail WHERE Id=:id",
                        param, new BeanPropertyRowMapper(TransaksiDetail.class));
    }

    public int addTransaksiDao(TransaksiDetail transaksiDetail) {
        String sql = "INSERT INTO TransaksiDetail(IdTransaksi, Item, Qty, Price) VALUES(:idTransaksi, :item, :qty, :price)";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("idTransaksi", transaksiDetail.getIdTransaksi());
        paramMap.put("item", transaksiDetail.getItem());
        paramMap.put("qty", transaksiDetail.getQty());
        paramMap.put("price", transaksiDetail.getPrice());
        return namedJdbcTemplate.update(sql, paramMap);
    }

    public int updateTransaksi(TransaksiDetail transaksiDetail) {
        String sql = "UPDATE TransaksiDetail SET IdTransaksi = :idTransaksi, Item = :item, Qty=:qty, Price= price where Id = :id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("idTransaksi", transaksiDetail.getIdTransaksi());
        paramMap.put("item", transaksiDetail.getItem());
        paramMap.put("qty", transaksiDetail.getQty());
        paramMap.put("price", transaksiDetail.getPrice());
        paramMap.put("id", transaksiDetail.getId());
        return namedJdbcTemplate.update(sql, paramMap);
    }
    
    public int deleteTransaksi(int id){
        String sql = "DELETE TransaksiDetail WHERE Id=:id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return namedJdbcTemplate.update(sql, paramMap);
    }
}
