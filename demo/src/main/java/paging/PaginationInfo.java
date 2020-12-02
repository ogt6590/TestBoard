package paging;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Data
@Getter
@Setter
public class PaginationInfo {

	/** 페이징 계산에 필요한 파라미터들이 담긴 클래스 */
	private Criteria criteria;

	/** 전체 데이터 개수 */
	private int totalRecordCount;

	/** 전체 페이지 개수 */
	private int totalPageCount;

	/** 페이지 리스트의 첫 페이지 번호 */
	private int firstPage;

	/** 페이지 리스트의 마지막 페이지 번호 */
	private int lastPage;

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}

	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	public int getLastRecordIndex() {
		return lastRecordIndex;
	}

	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	/** SQL의 조건절에 사용되는 첫 RNUM */
	private int firstRecordIndex;

	/** SQL의 조건절에 사용되는 마지막 RNUM 오라클처럼 limit 구문이 존재하지 않을떄 사용 mysql 사용안함*/
	private int lastRecordIndex;

	/** 이전 페이지 존재 여부 */
	private boolean hasPreviousPage;

	/** 다음 페이지 존재 여부 */
	private boolean hasNextPage;
	
	//잘못된 값이 들어왔을때 각각의 if 조건을 통해 기본값을 지정합니다
	//없는 페이지를 보여주거나 너무 많은 데이터를 출력하려면 문제가되서 기본설정으로 해줌
	//사용자의 요청 파라미터 정보를 가진 criteria를 criteria에저장 그래야만 페이지 번호를 계산할수있다
	public PaginationInfo(Criteria criteria) {
		if (criteria.getCurrentPageNo() < 1) {
			criteria.setCurrentPageNo(1);
		}
		if (criteria.getRecordsPerPage() < 1 || criteria.getRecordsPerPage() > 100) {
			criteria.setRecordsPerPage(10);
		}
		if (criteria.getPageSize() < 5 || criteria.getPageSize() > 20) {
			criteria.setPageSize(10);
		}

		this.criteria = criteria;
	}
	
	//넘어온 전체 데이터 개수를 전체 데이터 개수에 저장 
	//데이터 개수가 1개 이상이면 페이지 번호를 계산하는 calculation 메서드를 실행합니다.
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;

		if (totalRecordCount > 0) {
			calculation();
		}
	
	}
	
	//페이지 번호를 계산하는 calculation 메서드 실행
	private void calculation() {

		//전체 페이지 개수를 구함 
		totalPageCount = ((totalRecordCount - 1) / criteria.getRecordsPerPage()) + 1;
		//전체 페이지 수 (현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장)
		//전체가 5인데 현재가 6일수는 없기떄문이다
		if (criteria.getCurrentPageNo() > totalPageCount) {
			criteria.setCurrentPageNo(totalPageCount);
		}

		// 페이지 리스트의 첫 페이지 번호 
		firstPage = ((criteria.getCurrentPageNo() - 1) / criteria.getPageSize()) * criteria.getPageSize() + 1;

		//페이지 리스트의 마지막 페이지 번호  
		lastPage = firstPage + criteria.getPageSize() - 1;
		
		//마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장
		//전체가 35인데 36~40페이지는 필요가 없기떄문이다
		if (lastPage > totalPageCount) {
			lastPage = totalPageCount;
		}

		//sql limit 구문의 첫 번쨰 값에 들어갈 데이터 입니다 
		firstRecordIndex = (criteria.getCurrentPageNo() - 1) * criteria.getRecordsPerPage();

		//오라클 디비 사용용
		lastRecordIndex = criteria.getCurrentPageNo() * criteria.getRecordsPerPage();

		//이전 페이지 존재 여부 첫 페이지 번호가 1이면 false 아니면 true 
		hasPreviousPage = firstPage != 1;

		//다음 페이지 존재 여부  
		hasNextPage = (lastPage * criteria.getRecordsPerPage()) < totalRecordCount;
	}

}