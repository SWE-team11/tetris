package tetris.model;

import java.util.Date;

public class RecordModel {
    class Record {
        public int score;
        public Date createdAt;
    }

    private Record[] rankedRecords;
    //@TODO 저장 시 score 기준으로 정렬한 뒤 로컬에 덮어씌웁니다.

    public RecordModel() {
        //@TODO 생성 시 로컬의 데이터를 로드합니다.
    }
}
