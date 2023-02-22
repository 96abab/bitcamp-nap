
-- 게시글 작성자 번호를 저장하는 컬럼 추가
-- 작성자 번호는 app-memver 테이블의 PK 
alter table app_board
  add column writer int,
  add constraint app_board_fk foreign key (writer) references app_member(member_id);
