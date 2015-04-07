$(function () {
    $("input,select,textarea").not("[type=submit]").jqBootstrapValidation({
        preventSubmit: true,
        submitError: function ($form, event, errors) {
        }
    });
});
