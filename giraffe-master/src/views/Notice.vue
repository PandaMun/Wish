<template>
  <div>
    <v-row justify="center">
      <v-col cols="10" lg="10" xl="10">
        <div>
          <div>
            <div class="my-6">
              <h2 class="text-h2 text-center font-weight-bold mt-5">Notice</h2>
            </div>

            <v-dialog persistent v-model="write" max-width="600px">
              <template v-slot:activator="{ on, attrs }">
                <v-row class="px-3" justify="end">
                  <v-btn v-bind="attrs" v-on="on" class="px-3 green font-weight-bold white--text">
                    작성하기
                  </v-btn>
                </v-row>
              </template>
              <v-card>
                <v-card-title>
                  <span class="text-h5 font-weight-bold">공지사항 등록</span>
                </v-card-title>
                <v-card-text class="pb-0">
                  <v-container>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          label="공지사항 제목"
                          required
                          name="title"
                          v-model="title"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-textarea
                          label="공지사항 내용"
                          required
                          name="content"
                          v-model="content"
                        ></v-textarea>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" class="font-weight-bold" text @click="registQna">
                    등록
                  </v-btn>
                  <v-btn color="blue darken-1" class="font-weight-bold" text @click="write = false">
                    취소
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

            <v-row>
              <v-col
                cols="12"
                md="12"
                v-for="(qna, i) in qnaList"
                :key="i"
                v-if="selectPage * 5 - 1 >= i && (selectPage - 1) * 5 <= i"
              >
                <v-hover v-slot:default="{ hover }" open-delay="50" close-delay="50">
                  <div>
                    <v-card
                      flat
                      :color="hover ? 'white' : 'whitegrey'"
                      :elevation="hover ? 12 : 0"
                      hover
                    >
                      <v-card-text>
                        <v-avatar
                          v-if="qnaList[i].answer === false"
                          color="#F49D1A"
                          size="40"
                          class="authors"
                        >
                          <v-icon dark size="24">mdi-help</v-icon>
                        </v-avatar>
                        <v-avatar
                          v-if="qnaList[i].answer === true"
                          color="green"
                          size="40"
                          class="authors"
                        >
                          <v-icon dark size="24">mdi-check</v-icon>
                        </v-avatar>

                        <!-- <div class="text-h5 font-weight-bold primary--text pt-4">
                          {{ i }}
                        </div> -->
                        <div class="pt-1">
                          <v-dialog
                            persistent
                            v-model="dialog[i]"
                            width="700"
                            :retain-focus="false"
                          >
                            <template v-slot:activator="{ on, attrs }">
                              <v-btn
                                plain
                                class="text-h5 font-weight-bold primary--text"
                                v-bind="attrs"
                                v-on="on"
                                @click="findComment(qna.id)"
                              >
                                {{ qna.id }}. {{ qna.title }}
                              </v-btn>
                            </template>

                            <v-card>
                              <v-card-title class="text-h5 grey font-weight-bold lighten-2">
                                {{ qna.title }}
                              </v-card-title>

                              <!-- <v-card-text class="text-h5 pt-5 black--text font-weight-bold">
                                {{ qna.title }}
                              </v-card-text> -->
                              <v-card-text class="pt-5 black--text font-weight-bold">
                                {{ qna.content }}
                              </v-card-text>
                              <!-- <div style="display: flex; justify-content: center">
                                <v-card style="width: 90%" v-if="comment != ''">
                                  <v-card-text class="pt-5 pd-0 black--text font-weight-bold">
                                    <v-icon>mdi-account</v-icon> ssafy
                                  </v-card-text>
                                  <hr
                                    width="95%"
                                    color="lightgrey"
                                    style="margin-left: auto; margin-right: auto"
                                  />
                                  <v-card-text class="black--text">
                                    <v-text-field solo flat v-model="comment"></v-text-field>
                                  </v-card-text>
                                </v-card>
                              </div> -->

                              <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn
                                  color="blue darken-1"
                                  class="font-weight-bold"
                                  text
                                  @click="closeDialog(i)"
                                >
                                  확인
                                </v-btn>
                                <v-btn
                                  color="blue darken-1"
                                  class="font-weight-bold"
                                  text
                                  @click="openDialog2(i)"
                                >
                                  수정
                                </v-btn>
                                <v-btn
                                  color="blue darken-1"
                                  class="font-weight-bold"
                                  text
                                  @click="deleteQna(i)"
                                >
                                  삭제
                                </v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                          <v-dialog
                            persistent
                            v-model="dialog2[i]"
                            max-width="500px"
                            :retain-focus="false"
                          >
                            <v-card>
                              <v-card-title>
                                <span class="text-h5">Modify</span>
                              </v-card-title>
                              <v-card-text>
                                <v-container>
                                  <v-row>
                                    <v-col cols="12">
                                      <v-textarea v-model="mTitle" required></v-textarea>
                                    </v-col>
                                  </v-row>
                                </v-container>
                              </v-card-text>
                              <v-card-text>
                                <v-container>
                                  <v-row>
                                    <v-col cols="12">
                                      <v-textarea v-model="mContent" required></v-textarea>
                                    </v-col>
                                  </v-row>
                                </v-container>
                              </v-card-text>
                              <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn
                                  color="blue darken-1"
                                  class="font-weight-bold"
                                  text
                                  @click="modifyQna(i)"
                                >
                                  수정
                                </v-btn>
                                <v-btn
                                  color="blue darken-1"
                                  class="font-weight-bold"
                                  text
                                  @click="closeDialog2(i)"
                                >
                                  취소
                                </v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                          <v-dialog
                            persistent
                            v-model="dialog3[i]"
                            max-width="500px"
                            :retain-focus="false"
                          >
                            <v-card>
                              <v-card-title>
                                <span class="text-h5 font-weight-bold">답변 등록</span>
                              </v-card-title>
                              <v-card-text class="pb-0">
                                <v-container>
                                  <v-row>
                                    <v-col cols="12">
                                      <v-textarea v-model="rComment" required></v-textarea>
                                    </v-col>
                                  </v-row>
                                </v-container>
                              </v-card-text>
                              <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn
                                  color="blue darken-1"
                                  class="font-weight-bold"
                                  text
                                  @click="registComment(i)"
                                >
                                  등록
                                </v-btn>
                                <v-btn
                                  color="blue darken-1"
                                  class="font-weight-bold"
                                  text
                                  @click="closeDialog3(i)"
                                >
                                  취소
                                </v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                        </div>
                        <div class="text-body-1 py-4 pl-5">
                          <span>작성자 : {{ qna.userId }}</span>
                          <br />
                          <span>작성일시 : {{ qna.createDate }}</span>
                        </div>

                        <div>
                          <v-btn icon>
                            <v-icon>mdi-facebook</v-icon>
                          </v-btn>

                          <v-btn icon>
                            <v-icon>mdi-twitter</v-icon>
                          </v-btn>

                          <v-btn icon>
                            <v-icon>mdi-youtube</v-icon>
                          </v-btn>

                          <v-btn icon>
                            <v-icon>mdi-instagram</v-icon>
                          </v-btn>
                        </div>
                      </v-card-text>
                    </v-card>
                  </div>
                </v-hover>
              </v-col>
            </v-row>
            <div>
              <paginate
                :page-count="page"
                :page-range="3"
                :margin-pages="2"
                :click-handler="changePage"
                :prev-text="'<'"
                :next-text="'>'"
                :container-class="'pagination'"
                :page-class="'page-item'"
              >
              </paginate>
            </div>
          </div>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Vue from "vue";
import http from "@/util/http-common";
import Paginate from "vuejs-paginate";
Vue.component("paginate", Paginate);

export default {
  data() {
    return {
      selectPage: 1,
      page: 0,

      title: "",
      userId: "",
      content: "",

      mTitle: "",
      mContent: "",

      rComment: "",

      qnaList: [],
      comment: "",
      author: "",

      dialog: [],
      dialog2: [],
      dialog3: [],
      write: false,
    };
  },

  methods: {
    registQna() {
      let qnaInfo = {
        title: this.title,
        userId: "ssafy",
        content: this.content,
      };

      http.post("/wish/notice/", qnaInfo).then(({ data }) => {
        console.log(data);
        this.write = false;
        this.makeList();
        this.title = "";
        this.content = "";
      });
    },
    makeList() {
      http.get("/wish/notice/").then(({ data }) => {
        console.log(data);
        this.qnaList = data;
        if (this.qnaList.length == 0) {
          this.page = 1;
        } else {
          this.page = Math.floor((this.qnaList.length - 1) / 5 + 1);
        }
        console.log(this.page);
        this.dialog = Array.from({ length: this.qnaList.length }, () => false);
        this.dialog2 = Array.from({ length: this.qnaList.length }, () => false);
        this.dialog3 = Array.from({ length: this.qnaList.length }, () => false);
      });
    },
    findComment(i) {
      let url = "wish/notice/" + i;
      http.get(url).then(({ data }) => {
        console.log(data);
        this.comment = data.content;
        this.author = data.userId;
      });
    },
    registComment(i) {
      let url = "wish/admin/comment";
      http
        .post(url, {
          id: this.qnaList[i].id,
          userId: "ssafy",
          content: this.rComment,
        })
        .then(({ data }) => {
          console.log(data);
          this.qnaList[i].answer = true;
          this.comment = this.rComment;
          this.findComment(this.qnaList[i].id);
          this.closeDialog3(i);
          this.makeList();
        });
    },
    modifyQna(i) {
      let url = "wish/notice/";
      http
        .put(url, {
          id: this.qnaList[i].id,
          userId: "ssafy",
          title: this.mTitle,
          content: this.mContent,
        })
        .then(({ data }) => {
          console.log(data);
          this.qnaList[i].title = this.mTitle;
          this.qnaList[i].content = this.mContent;
          alert("성공적으로 수정되었습니다.");
          this.closeDialog2(i);
        });
    },
    deleteQna(i) {
      let url = "wish/notice/" + this.qnaList[i].id;
      http.delete(url).then(({ data }) => {
        console.log(data);
        alert("성공적으로 삭제되었습니다.");
        this.closeDialog(i);
        this.makeList();
      });
    },
    closeDialog(index) {
      this.$set(this.dialog, index, false);
      this.comment = "";
    },
    closeDialog2(index) {
      this.$set(this.dialog2, index, false);
    },
    closeDialog3(index) {
      this.$set(this.dialog3, index, false);
    },
    openDialog(index) {
      this.findComment(index);
      this.$set(this.dialog, index, true);
    },
    openDialog2(index) {
      this.mTitle = this.qnaList[index].title;
      this.mContent = this.qnaList[index].content;
      this.$set(this.dialog2, index, true);
    },
    openDialog3(index) {
      this.rComment = "";
      this.$set(this.dialog3, index, true);
    },
    changePage(pageNum) {
      this.selectPage = pageNum;
    },
  },
  created() {
    this.makeList();
  },
};
</script>

<style lang="css">
.authors {
  position: relative;
  top: -50px;
  margin-bottom: -50px;
}
.pagination {
  display: flex;
  list-style: none;
  margin: 24px;
  justify-content: center;
}
.pagination li {
  width: 30px;
  padding: 2px 6px;
  text-align: center;
  margin: 0 3px;
  border-radius: 6px;
  border: 1px solid #eee;
  color: #666;
}
.pagination li:hover {
  background: #e4dbd6;
}
.page-item a {
  color: #666;
  text-decoration: none;
}
.pagination li.active {
  background-color: #e7aa8d;
  color: #fff;
}
.pagination li.active a {
  color: #fff;
}
</style>
