package unittests.mocks;

import br.com.erudio.restwithspringbootandjavaerudio.data.vo.v1.BookVO;
import br.com.erudio.restwithspringbootandjavaerudio.model.BookModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {


    public BookModel mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }


    public List<BookModel> mockEntityList() {
        List<BookModel> bookModelList = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            bookModelList.add(mockEntity(i));

        }
        return bookModelList;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> bookVOList = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            bookVOList.add(mockVO(i));
        }
        return bookVOList;

    }

    public BookModel mockEntity(Integer number) {
        BookModel bookModel = new BookModel();
        bookModel.setAuthor("Michael C. Feathers" + number);
        bookModel.setPrice(49.00);
        bookModel.setTitle("Working effectively with legacy code" + number);
        bookModel.setLaunchDate(new Date());
        bookModel.setId(number.longValue());
        return bookModel;
    }


    public BookVO mockVO(Integer number) {
        BookVO bookVO = new BookVO();
        bookVO.setAuthor("Michael C. Feathers" + number);
        bookVO.setPrice(49.00);
        bookVO.setTitle("Working effectively with legacy code" + number);
        bookVO.setLaunchDate(new Date());
        bookVO.setKey(number.longValue());
        return bookVO;
    }
}
