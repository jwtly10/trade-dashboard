package com.jwtly10.tradedashboard.dao;

import com.jwtly10.tradedashboard.model.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TradeDAOImpl implements TradeDAO<Trade> {
private static final Logger log = LoggerFactory.getLogger(TradeDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    RowMapper<Trade> rowMapper = (rs, rowNum) -> {
        Trade trade = new Trade();
        trade.setTicketID(rs.getInt("ticketId"));
        trade.setAccountID(rs.getInt("accountID"));
        trade.setTradeType(rs.getString("tradeType"));
        trade.setSymbol(rs.getString("symbol"));
        trade.setSize(rs.getFloat("size"));
        trade.setPrice(rs.getFloat("price"));
        trade.setSl(rs.getFloat("sl"));
        trade.setTp(rs.getFloat("tp"));
        trade.setSwap(rs.getFloat("swap"));
        trade.setProfit(rs.getFloat("profit"));
        trade.setOpened(rs.getTimestamp("opened"));
        trade.setClosed(rs.getTimestamp("closed"));
        trade.setCreated(rs.getTimestamp("created"));
        trade.setOutcome(rs.getString("outcome"));
        return trade;
    };

    public TradeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Trade> list() {
        String sql = "SELECT ticketID, accountID, tradeType, symbol, size, price, sl, tp, swap, profit,opened, closed, created, outcome FROM trades_tb";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Trade trade) {
        String sql = "INSERT INTO trades_tb (ticketID, accountID, tradeType, symbol, size, price, sl, tp, swap, profit, opened, closed) values (?,?,?,?,?,?,?,?,?,?,?,?);";
        int res = jdbcTemplate.update(sql,
                trade.getTicketID(),
                trade.getAccountID(),
                trade.getTradeType(),
                trade.getSymbol(),
                trade.getSize(),
                trade.getPrice(),
                trade.getSl(),
                trade.getTp(),
                trade.getSwap(),
                trade.getProfit(),
                trade.getOpened(),
                trade.getClosed());
        if (res == 1){
            log.info("New closed trade created: " + trade.getTicketID());
        }
   }

    @Override
    public List<Trade> get(int id) {
        String sql = "SELECT ticketID, accountID, tradeType, symbol, size, price, sl, tp, swap, profit,opened, closed, created, outcome FROM trades_tb WHERE accountID = ?";
        List<Trade> trades = null;
        try{
            trades = jdbcTemplate.query(sql, rowMapper, id);
        }catch (DataAccessException ex){
            log.info("Trades not found for account: " + id);
        }
        return trades;
    }

    @Override
    public Optional<Trade> get(int ticketID, int accountID) {
        String sql = "SELECT ticketID, accountID, tradeType, symbol, size, price, sl, tp, swap, profit,opened, closed, created, outcome FROM trades_tb WHERE accountID = ? AND ticketID = ?";
        Trade trade = null;
        try{
            trade = jdbcTemplate.queryForObject(sql, rowMapper, accountID, ticketID);
        }catch (DataAccessException ex){
            log.info("Trade for found for ticketID: " + ticketID + " / AccountID: "+ accountID);
        }
        return Optional.ofNullable(trade);
    }

    @Override
    public void update(Trade trade, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
