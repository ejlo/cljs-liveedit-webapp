(defun clojurescript-repl ()
  (interactive)
  (paredit-mode +1)
  (run-lisp "lein trampoline cljsbuild repl-listen"))

(defun clojurescript-set-namespace-from-buffer ()
  (interactive)
  (save-excursion
    (goto-char (point-min)) ; beginning of buffer
    (lisp-eval-defun)))

(define-key clojure-mode-map (kbd "C-x C-l") 'clojurescript-repl)
(define-key clojure-mode-map (kbd "C-x C-n") 'clojurescript-set-namespace-from-buffer)
