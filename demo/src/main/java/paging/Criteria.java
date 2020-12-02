package paging;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Data
@Getter
@Setter
public class Criteria {

	//현재 페이지 번호
	private int currentPageNo;

	//페이지당 출력할 데이터 개수 
	private int recordsPerPage;

	//화면 하단에 출력핧 페이지 사이즈 
	private int pageSize;

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	//기본값 
	public Criteria() {
		//현재 페이지 번호
		this.currentPageNo = 1;
		//페이지당 출력할 데이터 개수 
		this.recordsPerPage = 10;
		//화면 하단에 출력핧 페이지 사이즈 10 으로하면 1 ~ 10 까지 페이지 보임
		this.pageSize = 10;
	}


}
