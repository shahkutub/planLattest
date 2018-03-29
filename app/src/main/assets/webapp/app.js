/// <reference path="typings/angularjs/angular.d.ts" />
/// <reference path="typings/jquery/jquery.d.ts" />
var app = angular.module("myApp", ['ngMaterial', 'ngRateIt']);
var comp = app.component("qscomp", {
    template: "\n<md-card ng-attr-style=\"{{BackgroundColor}}\" ng-repeat=\"x in primaryqs track by x.surveyQuestionTableId\">\n    <md-card-title>\n        <md-card-title-text>\n            <h4 ng-if=\"x.questionType!='3' \">{{x.questionName}}</h4>\n        </md-card-title-text>\n    </md-card-title>\n    <md-card-content>\n        <div ng-if=\"x.questionType=='1'\" ng-attr-id='{{\"qt1sq\"+x.surveyQuestionTableId}}' ng-init=\"qt1choices = getChoicesByQsId(x.surveyQuestionTableId)\">\n            <div ng-repeat=\"r in qt1choices\">\n                <md-checkbox ng-model=\"r.checked\" aria-label=\"{{r.answer_choice}}\" ng-change=\"val(r,x.surveyQuestionTableId,'qt1sq'+x.surveyQuestionTableId)\">\n                    {{r.answer_choice}}<i>{{\" (\"+r.ansNo+\")\"}}</i>\n                </md-checkbox>\n            </div>\n        </div>\n        <div ng-if=\"x.questionType=='2'\" ng-attr-id='{{\"qt2sq\"+x.surveyQuestionTableId}}'>\n            <md-input-container>\n                <label>Select</label>\n                <md-select ng-init=\"qt2choices = getChoicesByQsId(x.surveyQuestionTableId)\" ng-change=\"SelectChange(AnswerChoice,x.surveyQuestionTableId,'qt2sq'+x.surveyQuestionTableId)\"\n                    ng-model=\"AnswerChoice\">\n                    <md-option ng-repeat=\"r in qt2choices\" ng-value=\"r.id\">\n                        {{r.answer_choice}}\n                    </md-option>\n                </md-select>\n            </md-input-container>\n        </div>\n        <div ng-if=\"x.questionType=='3'\" ng-attr-id='{{\"qt3sq\"+x.surveyQuestionTableId}}'>\n            <md-input-container class=\"md-block\">\n                <label>{{x.questionName}}</label>\n                <input md-no-asterisk ng-model=\"AnswerQsType3\" ng-change=\"TextChange(AnswerQsType3,x.surveyQuestionTableId)\">\n            </md-input-container>\n        </div>\n        <div ng-if=\"x.questionType=='4'\" ng-attr-id='{{\"qt4sq\"+x.surveyQuestionTableId}}'>\n            <!--<textarea class=\"form-control\" placeholder=\"{{x.questionName}}\" ng-change=\"TextChange(AnswerQsType4,x.surveyQuestionTableId)\"\n            ng-model=\"AnswerQsType4\"></textarea>-->\n            <md-input-container class=\"md-block\">\n                <textarea ng-change=\"TextChange(AnswerQsType4,x.surveyQuestionTableId)\" ng-model=\"AnswerQsType4\" rows=\"5\" md-select-on-focus></textarea>\n            </md-input-container>\n        </div>\n        <div ng-init=\"qt5choices = getChoicesByQsId(x.surveyQuestionTableId)\" ng-if=\"x.questionType=='5'\" ng-attr-id='{{\"qt5sq\"+x.surveyQuestionTableId}}'>\n            <div>\n                <div ng-repeat=\"r in qt5choices \">\n                    <b><p for=\"label\">{{r.answer_choice}}</p></b>\n                    <md-input-container>\n                        <label>Select</label>\n                        <md-select ng-model=\"AnswerQsType5\" ng-change=\"Qstype5Change(x.surveyQuestionTableId,r,AnswerQsType5)\">\n                            <md-option ng-repeat=\"r in qt5choices \" ng-attr-value=\"{{$index+1}}\">\n                                {{$index+1}}\n                            </md-option>\n                        </md-select>\n                    </md-input-container>\n                </div>\n            </div>\n        </div>\n        <div ng-if=\"x.questionType=='6'\" ng-attr-id='{{\"qt6sq\"+x.surveyQuestionTableId}}'>\n            <ng-rate-it ng-model=\"$ctrl.rate\" step='1' max='x.scale' ng-click='RateClick(x.surveyQuestionTableId)'></ng-rate-it>\n        </div>\n\n        <div ng-init=\"qt7choices = getChoicesByQsId(x.surveyQuestionTableId)\" ng-if=\"x.questionType=='7'\" ng-attr-id='{{\"qt7sq\"+x.surveyQuestionTableId}}'>\n            <div style=\"margin-top: 4px;\">\n                <div ng-repeat=\"r in qt7choices\">\n                    <md-input-container class=\"md-block\">\n                        <label>{{r.answer_choice}}</label>\n                        <input md-no-asterisk ng-model=\"AnswerQsType7\" ng-change=\"QsType7Change(x.surveyQuestionTableId,r,AnswerQsType7)\">\n                    </md-input-container>\n                </div>\n            </div>\n        </div>\n        <div ng-if=\"x.questionType=='8'\" ng-attr-id='{{\"qt8sq\"+x.surveyQuestionTableId}}'>\n            <!--<input class=\"form-control\" type=\"date\" placeholder=\"{{x.questionName}}\" ng-change=\"TextChange(AnswerQsType8,x.surveyQuestionTableId)\"\n            ng-model=\"AnswerQsType8\">-->\n            <md-datepicker ng-change=\"TextChange(AnswerQsType8,x.surveyQuestionTableId)\" ng-model=\"AnswerQsType8\" md-placeholder=\"{{x.questionName}}\"></md-datepicker>\n        </div>\n        <div ng-if=\"x.questionType=='9'\" ng-init=\"qt9choices = getChoicesByQsId(x.surveyQuestionTableId)\" ng-attr-id=\"{{'qt9sq'+x.surveyQuestionTableId}}\">\n            <div style=\"padding-left: 20px\" class=\"radio\">\n                <!--<input ng-attr-name=\"rdo{{x.surveyQuestionTableId}}\" type=\"radio\" ng-attr-value=\"{{r.id}}\" ng-model=\"x.qs9\" ng-click=\"rdoClick(x.qs9,x.surveyQuestionTableId,'qt9sq'+x.surveyQuestionTableId)\">\n            <lable style=\" font-weight: 700;\">{{r.answer_choice}}</lable>\n            <i>{{\" (\"+r.ansNo+\")\"}}</i>-->\n\n                <md-radio-group ng-model=\"x.qs9\" ng-change=\"rdoClick(x.qs9,x.surveyQuestionTableId,'qt9sq'+x.surveyQuestionTableId)\">\n                    <md-radio-button ng-repeat=\"r in qt9choices\" ng-attr-value=\"{{r.id}}\">\n                        {{r.answer_choice}}\n                        <i>{{\" (\"+r.ansNo+\")\"}}</i>\n                    </md-radio-button>\n                </md-radio-group>\n            </div><br>\n        </div>\n    </md-card-content>\n</md-card>\n        ",
    bindings: {
        surveyquestionchoiceid: "@",
        id: '@',
        parentid: '@'
    },
    controller: function ($scope, $http, $compile, $rootScope) {
        $scope.primaryqs = [];
        $scope.BackgroundColor = "background-color: #ffffff";
        $scope.range = function (qs) {
            var ns = [];
            for (var index = 0; index < qs.scale; index++) {
                ns.push(index);
            }
            return ns;
        };
        var ctrl = this;
        this.$onInit = function () {
            var dat = JSON.parse(and.GetPrimaryQs());
            $scope.primaryqs = dat.filter(function (r) { return r.is_primary_question == "1"; });
            if (ctrl.surveyquestionchoiceid) {
                if ($rootScope.isGray)
                    $scope.BackgroundColor = "background-color: #ffffff";
                else
                    $scope.BackgroundColor = "background-color: #f9f9f9";
                $rootScope.isGray = !$rootScope.isGray;
                var surveyQuestionIds = and.Getsurvey_question_idbysurveylogic(parseInt(ctrl.surveyquestionchoiceid));
                var arrSurveyQuestionIds = JSON.parse(surveyQuestionIds);
                $scope.primaryqs = dat.filter(function (i) { return arrSurveyQuestionIds.filter(function (s) { return i.surveyQuestionTableId == s; }) > 0; });
            }
            //init rootscope
            if (!$rootScope.ansStore) {
                $rootScope.ansStore = {};
            }
            //set Parent is
            if (!ctrl.parentid) {
                ctrl.parentid = 0;
            }
        };
        $scope.val = function (choice, surveyQuestionTableId, parentId) {
            var checked = choice.checked;
            var id = "sq" + surveyQuestionTableId;
            if (checked) {
                var newScope = $scope.$new(true, $scope);
                newScope = angular.merge(newScope);
                var html = "<qscomp id='" + id + "' surveyquestionchoiceid='" + choice.id + "' parentid='" + surveyQuestionTableId + "'></qscomp>";
                $("#" + parentId).append($compile(html)(newScope));
                //store answer
                $rootScope.ansStore["q_" + surveyQuestionTableId + "_" + ctrl.parentid] = choice.id;
            }
            else {
                $("#" + id).remove();
            }
        };
        var previous;
        $scope.rdoClick = function (value, surveyQuestionTableId, parentId) {
            var id = "sq" + surveyQuestionTableId;
            var newScope = $scope.$new(true, $scope);
            newScope = angular.merge(newScope);
            $("#" + id).remove();
            var html = "<qscomp id='" + id + "' surveyquestionchoiceid='" + value + "' parentid='" + surveyQuestionTableId + "'></qscomp>";
            $("#" + parentId).append($compile(html)(newScope));
            //store answer
            $rootScope.ansStore["q_" + surveyQuestionTableId + "_" + ctrl.parentid] = value;
        };
        $scope.SelectChange = function (value, surveyQuestionTableId, parentId) {
            var id = "sq" + surveyQuestionTableId;
            var newScope = $scope.$new(true, $scope);
            newScope = angular.merge(newScope);
            $("#" + id).remove();
            var html = "<qscomp id='" + id + "' surveyquestionchoiceid='" + value + "' parentid='" + surveyQuestionTableId + "'></qscomp>";
            $("#" + parentId).append($compile(html)(newScope));
            //store answer
            $rootScope.ansStore["q_" + surveyQuestionTableId + "_" + ctrl.parentid] = value;
        };
        $scope.TextChange = function (value, surveyQuestionTableId) {
            $rootScope.ansStore["q_" + surveyQuestionTableId + "_" + ctrl.parentid + "_t"] = value;
        };
        $scope.getChoicesByQsId = function (questionId) {
            var str = and.GetChoicesById(questionId);
            return JSON.parse(and.GetChoicesById(questionId));
        };
        $scope.Qstype5Change = function (surveyQuestionTableId, r, AnswerQsType5) {
            $rootScope.ansStore["q_" + surveyQuestionTableId + "_" + ctrl.parentid] = AnswerQsType5;
        };
        $scope.RateClick = function (qsid) {
            $rootScope.ansStore["q_" + qsid + "_" + ctrl.parentid] = ctrl.rate;
        };
        $scope.QsType7Change = function (surveyQuestionTableId, r, AnswerQsType7) {
            $rootScope.ansStore["q_" + surveyQuestionTableId + "_" + ctrl.parentid + "_t"] = AnswerQsType7;
        };
    }
});
var testCtrl = app.controller("tst", function ($scope, $rootScope) {
    $scope.isGeneral = true;
    $scope.Visible = [true, false, false, false];
    this.$onInit = function () {
        $scope.isGeneral = and.isGeneral();
        if ($scope.isGeneral) {
            $scope.SurveyList = JSON.parse(and.GetSurveyList(1));
            $scope.SetVisible(2);
            $scope.setHeading("Survey List");
        }
        else {
            $scope.SetVisible(0);
            $scope.ProjectList = JSON.parse(and.GetProjectList());
            $scope.setHeading("Project List");
        }
    };
    $scope.SetVisible = function (index) {
        $scope.Visible.forEach(function (v, i, a) { return a[i] = false; });
        $scope.Visible[index] = true;
        $scope.currentVisibleIndex = index;
        switch (index) {
            case 0:
                $scope.setHeading("Audio Statement");
                if ($scope.isGeneral)
                    and.closeActivity();
                break;
            case 1:
                $scope.setHeading("Project List");
                if ($scope.isGeneral)
                    and.closeActivity();
                break;
            case 2:
                $scope.setHeading("Survey List");
                break;
            default:
                $scope.setHeading("");
                break;
        }
    };
    $scope.ProjectClick = function (proj) {
        $scope.selectedProject = proj;
        $scope.SurveyList = JSON.parse(and.GetSurveyList(proj.categoryId));
        $scope.checkProjectCompleted();
        if (proj.completed)
            return;
        $scope.SetVisible(2);
        $scope.setHeading("Survey List");
    };
    $scope.SurveyClick = function (survey) {
        if (survey.completed)
            return;
        $scope.SetVisible(3);
        and.setSurveyId(survey.id);
        $scope.selectedSurvey = survey;
        $scope.setHeading(survey.name);
    };
    $scope.OK = function () {
        var list = Object.keys($rootScope.ansStore).map(function (i) {
            var ids = i.split("_");
            var qsid = ids[1];
            var parentId = ids[2];
            var isText = ids.length > 3 ? 1 : 0;
            var value = $rootScope.ansStore[i];
            return { questionId: qsid, answer: value, parentId: parentId, isText: isText };
        });
        and.SaveAns(JSON.stringify(list));
        $scope.selectedSurvey.completed = true;
        $scope.SetVisible(2);
        $scope.checkProjectCompleted();
    };
    $scope.checkProjectCompleted = function () {
        var anyNotCompleted = $scope.SurveyList.filter(function (i) { return !i.completed; }).length > 0;
        if (!anyNotCompleted)
            $scope.selectedProject.completed = true;
    };
    $scope.BackButtonClick = function () {
        if ($scope.currentVisibleIndex) {
            var i = $scope.currentVisibleIndex - 1;
            if (i != -1)
                $scope.SetVisible(i);
            else
                and.closeActivity();
        }
        else {
            and.closeActivity();
        }
    };
    $scope.setHeading = function (txt) { return $("#pagehead").html(txt); };
    $scope.ExitButtonClick = function () {
        and.closeActivity();
    };
    $scope.isRecording = false;
    $scope.isRecorded = false;
    $scope.BtnRecord = function () {
        document.getElementById("imgrecord").src = "recording.svg";
        if (!$scope.isRecording) {
            and.StartRecording();
            $scope.isRecording = true;
        }
    };
    $scope.BtnStopRecord = function () {
        document.getElementById("imgrecord").src = "record.svg";
        if ($scope.isRecording) {
            and.StopRecording();
            $scope.isRecording = false;
            $scope.isRecorded = true;
        }
    };
    $scope.BtnPlayRecord = function () {
        if ($scope.isRecorded) {
            and.StartPlaying();
        }
    };
    $scope.BtnSaveRecord = function () {
        and.SaveAudio();
        $scope.SetVisible(1);
    };
});
