package controller;

public interface CommentAbstract {
        void createComment(String content, int BID, int UID);
        void deleteComment(int CID);
        void updateComment(int CID, String content);
}
