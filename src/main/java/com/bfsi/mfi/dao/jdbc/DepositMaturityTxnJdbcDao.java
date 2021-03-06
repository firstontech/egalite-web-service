package com.bfsi.mfi.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bfsi.mfi.dao.DepositMaturityTxnDao;
import com.bfsi.mfi.entity.DepositMaturityTxnTracker;
import com.bfsi.mfi.query.ApplicationQuery;

@Repository("depositMaturityTxnDao")
public class DepositMaturityTxnJdbcDao  extends MaintenanceJdbcDao<DepositMaturityTxnTracker> implements DepositMaturityTxnDao{
	@Override
	protected String getSelectAllQuery() {
		return ApplicationQuery.DEPM_QUERY_SELECT_ALL;
	}
	
	@Override
	protected RowMapper<DepositMaturityTxnTracker> getRowMapper() {
		return new DepositMaturityMapper();
	}	
	
	protected RowMapper<DepositMaturityTxnTracker> getDepositMaturityMapper() {
		return new DepositMaturityMapper();
	}	
	
	private static final class DepositMaturityMapper implements RowMapper<DepositMaturityTxnTracker> 
	{
		public DepositMaturityTxnTracker mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			DepositMaturityTxnTracker depositMaturity=new DepositMaturityTxnTracker();
			depositMaturity.setId(rs.getString("MBS_TXN_ID"));
			depositMaturity.setAgendaId(rs.getString("AGENDA_ID"));
			depositMaturity.setSeqNo(rs.getLong("SEQ_NO"));
			depositMaturity.setTxnCode(rs.getString("TXN_CODE"));
			depositMaturity.setCbsAcRefNo(rs.getString("CBS_AC_REF_NO"));
			depositMaturity.setBranchCode(rs.getString("BRANCH_CODE"));
			depositMaturity.setCustomerId(rs.getString("CUSTOMER_ID"));
			depositMaturity.setCustomerFullName(rs.getString("CUSTOMER_FULL_NAME"));
			//depositMaturity.setAgendaId(rs.getString("AGENT_ID"));
			//Changes For Asomi Nirmal kanna S 
			depositMaturity.setAgentId(rs.getString("AGENT_ID"));
			depositMaturity.setAgentName(rs.getString("AGENT_NAME"));
			depositMaturity.setLocationCode(rs.getString("LOCATION_CODE"));
			depositMaturity.setLocationDesc(rs.getString("LOCATION_DESC"));
			depositMaturity.setMaturityAmount(rs.getDouble("MATURITY_AMOUNT"));
			depositMaturity.setAmountPaidOut(rs.getDouble("AMOUNT_PAID_OUT"));
			depositMaturity.setTxnNarrative(rs.getString("TXN_NARRATIVE"));
			depositMaturity.setTxnInitTime(rs.getDate("TXN_INIT_TIME"));
			depositMaturity.setTxnSyncTime(rs.getDate("TXN_SYNC_TIME"));
			depositMaturity.setTxnStatusDesc(rs.getString("TXN_STATUS_DESC"));
			depositMaturity.setTxnErrCode(rs.getString("TXN_ERR_CODE"));
			depositMaturity.setTxnErrMessage(rs.getString("TXN_ERR_MESSAGE"));
			/*For Tag No : EGA-MN15-000021 Starts*/
			depositMaturity.setGeneratedSms(rs.getString("GENERATED_SMS"));
			depositMaturity.setSmsMobileNo(rs.getString("SMS_MOBILE_NO"));
			depositMaturity.setSmsContent(rs.getString("SMS_CONTENT"));
			depositMaturity.setGeneratedRevr(rs.getString("GENERATED_REVR"));
			depositMaturity.setFullPartInd(rs.getString("FULL_PART_IND"));
			depositMaturity.setSyncType(rs.getString("SYNC_TYPE"));
			/*For Tag No : EGA-MN15-000021 End*/
			
			
			return depositMaturity;
		}
	}
	@Override
	public DepositMaturityTxnTracker get(String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		return jdbcTemplate.queryForObject(ApplicationQuery.DEPM_QUERY_SELECT_ALL_DISB, params, getDepositMaturityMapper());
	}
	

	@Override
	protected String getInsertQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getUpdateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getDeleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
