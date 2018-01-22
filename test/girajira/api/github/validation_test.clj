(ns girajira.api.github.validation-test
  (:require [midje.sweet :refer :all]
            [girajira.api.github.validation :refer :all]))

(facts "when getting tag data from the pull request body"
  (fact "it returns a map containing the tag data"
    (girajira-tag-data "text\r\ngirajira:ca-123:doing_done:done\n\ntext") =>
      {:issue "ca-123"
       :open "doing done"
       :merge "done"}
    (girajira-tag-data "text\r\ngirajira:ca-123:code_review:done_is_done\n\ntext") =>
      {:issue "ca-123"
       :open "code review"
       :merge "done is done"}))

(facts "when validating pull request data"
  (fact "it returns false when a key is missing"
    (valid-pull-request? {:body "a" :target "b" :user "c"}) => false
    (valid-pull-request? {:action "a" :body "b" :target "c"}) => false
    (valid-pull-request? {:action "a" :body "b" :user "c"}) => false)

 (fact "it returns false when :target is something other than opened or merged"
   (valid-pull-request? {:action "invalid" :body "girajira:ca-123:doing_done:done" :target "c" :user "d"}) => false)

 (fact "it returns false when :body does not contain a valid tag"
   (valid-pull-request? {:action "merged" :body "thing\r\n  girajira:ca-657:column \ntest" :target "c" :user "d"}) => false
   (valid-pull-request? {:action "merged" :body "thing\r\n girajira:ca-657:column \ntest" :target "c" :user "d"}) => false
   (valid-pull-request? {:action "merged" :body "thing\r\n girajira:ca-657:: \ntest" :target "c" :user "d"}) => false
   (valid-pull-request? {:action "merged" :body "thing\r\n fulano:ca-657:column \ntest" :target "c" :user "d"}) => false
   (valid-pull-request? {:action "merged" :body "thing\r\n pr text \ntest" :target "c" :user "d"}) => false)

 (fact "it returns true when the pull request is valid"
   (valid-pull-request?
     {:action "opened"
      :body "hello\r\ngirajira:ca-123:doing_done:done"
      :target "master"
      :user "fulano"}) => true
   (valid-pull-request?
     {:action "merged"
      :body "bla bla\r\n\n\n   girajira:ca-123:code_review:done\r\n\n\n\nblablablabla"
      :target "master"
      :user "fulano"}) => true))
