/// <reference path="typings/angularjs/angular.d.ts" />
/// <reference path="typings/jquery/jquery.d.ts" />
declare var $ctrl: any;
declare var and: any;//android
interface Answer {
    questionId: number; choiceId: number; answer: any;
}
var app = angular.module("myApp", ['ngMaterial', 'ngRateIt']);
var comp = app.component("qscomp", {
    template: `
<md-card ng-attr-style="{{BackgroundColor}}" ng-repeat="x in primaryqs track by x.surveyQuestionTableId">
    <md-card-title>
        <md-card-title-text>
            <h4 ng-if="x.questionType!='3' ">{{x.questionName}}</h4>
        </md-card-title-text>
    </md-card-title>
    <md-card-content>
        <div ng-if="x.questionType=='1'" ng-attr-id='{{"qt1sq"+x.surveyQuestionTableId}}' ng-init="qt1choices = getChoicesByQsId(x.surveyQuestionTableId)">
            <div ng-repeat="r in qt1choices">
                <md-checkbox ng-model="r.checked" aria-label="{{r.answer_choice}}" ng-change="val(r,x.surveyQuestionTableId,'qt1sq'+x.surveyQuestionTableId)">
                    {{r.answer_choice}}<i>{{" ("+r.ansNo+")"}}</i>
                </md-checkbox>
            </div>
        </div>
        <div ng-if="x.questionType=='2'" ng-attr-id='{{"qt2sq"+x.surveyQuestionTableId}}'>
            <md-input-container>
                <label>Select</label>
                <md-select ng-init="qt2choices = getChoicesByQsId(x.surveyQuestionTableId)" ng-change="SelectChange(AnswerChoice,x.surveyQuestionTableId,'qt2sq'+x.surveyQuestionTableId)"
                    ng-model="AnswerChoice">
                    <md-option ng-repeat="r in qt2choices" ng-value="r.id">
                        {{r.answer_choice}}
                    </md-option>
                </md-select>
            </md-input-container>
        </div>
        <div ng-if="x.questionType=='3'" ng-attr-id='{{"qt3sq"+x.surveyQuestionTableId}}'>
            <md-input-container class="md-block">
                <label>{{x.questionName}}</label>
                <input md-no-asterisk ng-model="AnswerQsType3" ng-change="TextChange(AnswerQsType3,x.surveyQuestionTableId)">
            </md-input-container>
        </div>
        <div ng-if="x.questionType=='4'" ng-attr-id='{{"qt4sq"+x.surveyQuestionTableId}}'>
            <!--<textarea class="form-control" placeholder="{{x.questionName}}" ng-change="TextChange(AnswerQsType4,x.surveyQuestionTableId)"
            ng-model="AnswerQsType4"></textarea>-->
            <md-input-container class="md-block">
                <textarea ng-change="TextChange(AnswerQsType4,x.surveyQuestionTableId)" ng-model="AnswerQsType4" rows="5" md-select-on-focus></textarea>
            </md-input-container>
        </div>
        <div ng-init="qt5choices = getChoicesByQsId(x.surveyQuestionTableId)" ng-if="x.questionType=='5'" ng-attr-id='{{"qt5sq"+x.surveyQuestionTableId}}'>
            <div>
                <div ng-repeat="r in qt5choices ">
                    <b><p for="label">{{r.answer_choice}}</p></b>
                    <md-input-container>
                        <label>Select</label>
                        <md-select ng-model="AnswerQsType5" ng-change="Qstype5Change(x.surveyQuestionTableId,r,AnswerQsType5)">
                            <md-option ng-repeat="r in qt5choices " ng-attr-value="{{$index+1}}">
                                {{$index+1}}
                            </md-option>
                        </md-select>
                    </md-input-container>
                </div>
            </div>
        </div>
        <div ng-if="x.questionType=='6'" ng-attr-id='{{"qt6sq"+x.surveyQuestionTableId}}'>
            <ng-rate-it ng-model="$ctrl.rate" step='1' max='x.scale' ng-click='RateClick(x.surveyQuestionTableId)'></ng-rate-it>
        </div>

        <div ng-init="qt7choices = getChoicesByQsId(x.surveyQuestionTableId)" ng-if="x.questionType=='7'" ng-attr-id='{{"qt7sq"+x.surveyQuestionTableId}}'>
            <div style="margin-top: 4px;">
                <div ng-repeat="r in qt7choices">
                    <md-input-container class="md-block">
                        <label>{{r.answer_choice}}</label>
                        <input md-no-asterisk ng-model="AnswerQsType7" ng-change="QsType7Change(x.surveyQuestionTableId,r,AnswerQsType7)">
                    </md-input-container>
                </div>
            </div>
        </div>
        <div ng-if="x.questionType=='8'" ng-attr-id='{{"qt8sq"+x.surveyQuestionTableId}}'>
            <!--<input class="form-control" type="date" placeholder="{{x.questionName}}" ng-change="TextChange(AnswerQsType8,x.surveyQuestionTableId)"
            ng-model="AnswerQsType8">-->
            <md-datepicker ng-change="TextChange(AnswerQsType8,x.surveyQuestionTableId)" ng-model="AnswerQsType8" md-placeholder="{{x.questionName}}"></md-datepicker>
        </div>
        <div ng-if="x.questionType=='9'" ng-init="qt9choices = getChoicesByQsId(x.surveyQuestionTableId)" ng-attr-id="{{'qt9sq'+x.surveyQuestionTableId}}">
            <div style="padding-left: 20px" class="radio">
                <!--<input ng-attr-name="rdo{{x.surveyQuestionTableId}}" type="radio" ng-attr-value="{{r.id}}" ng-model="x.qs9" ng-click="rdoClick(x.qs9,x.surveyQuestionTableId,'qt9sq'+x.surveyQuestionTableId)">
            <lable style=" font-weight: 700;">{{r.answer_choice}}</lable>
            <i>{{" ("+r.ansNo+")"}}</i>-->

                <md-radio-group ng-model="x.qs9" ng-change="rdoClick(x.qs9,x.surveyQuestionTableId,'qt9sq'+x.surveyQuestionTableId)">
                    <md-radio-button ng-repeat="r in qt9choices" ng-attr-value="{{r.id}}">
                        {{r.answer_choice}}
                        <i>{{" ("+r.ansNo+")"}}</i>
                    </md-radio-button>
                </md-radio-group>
            </div><br>
        </div>
    </md-card-content>
</md-card>
        `,
    bindings: {
        surveyquestionchoiceid: "@",
        id: '@',
        parentid: '@'
    },
    controller: function ($scope, $http: angular.IHttpService, $compile: angular.ICompileService, $rootScope) {
        $scope.primaryqs = [];
        $scope.BackgroundColor = "background-color: #ffffff";

        $scope.range = (qs) => {
            var ns: number[] = [];
            for (var index = 0; index < qs.scale; index++) {
                ns.push(index);
            }
            return ns;
        };

        var ctrl = this;
        this.$onInit = function () {
            var dat = JSON.parse(and.GetPrimaryQs());
            $scope.primaryqs = dat.filter(r => r.is_primary_question == "1");
            if (ctrl.surveyquestionchoiceid) {
                if ($rootScope.isGray)
                    $scope.BackgroundColor = "background-color: #ffffff";
                else
                    $scope.BackgroundColor = "background-color: #f9f9f9";
                $rootScope.isGray = !$rootScope.isGray;

                var surveyQuestionIds = and.Getsurvey_question_idbysurveylogic(parseInt(ctrl.surveyquestionchoiceid));
                var arrSurveyQuestionIds = JSON.parse(surveyQuestionIds);
                $scope.primaryqs = dat.filter(i => arrSurveyQuestionIds.filter(s => i.surveyQuestionTableId == s) > 0);
            }

            //init rootscope
            if (!$rootScope.ansStore) {
                $rootScope.ansStore = {};
            }

            //set Parent is
            if (!ctrl.parentid) {
                ctrl.parentid = 0;
            }
        }

        $scope.val = (choice, surveyQuestionTableId, parentId) => {
            var checked: boolean = choice.checked;
            var id = `sq${surveyQuestionTableId}`;
            if (checked) {
                var newScope = $scope.$new(true, $scope);
                newScope = angular.merge(newScope);
                var html = `<qscomp id='${id}' surveyquestionchoiceid='${choice.id}' parentid='${surveyQuestionTableId}'></qscomp>`;
                $("#" + parentId).append($compile(html)(newScope));
                //store answer
                $rootScope.ansStore[`q_${surveyQuestionTableId}_${ctrl.parentid}`] = choice.id;
            } else {
                $("#" + id).remove();
            }
        };

        var previous: any;

        $scope.rdoClick = (value, surveyQuestionTableId, parentId) => {
            var id = `sq${surveyQuestionTableId}`;

            var newScope = $scope.$new(true, $scope);
            newScope = angular.merge(newScope);
            $("#" + id).remove();
            var html = `<qscomp id='${id}' surveyquestionchoiceid='${value}' parentid='${surveyQuestionTableId}'></qscomp>`;
            $("#" + parentId).append($compile(html)(newScope));

            //store answer
            $rootScope.ansStore[`q_${surveyQuestionTableId}_${ctrl.parentid}`] = value;
        };
        $scope.SelectChange = (value, surveyQuestionTableId, parentId) => {
            var id = `sq${surveyQuestionTableId}`;
            var newScope = $scope.$new(true, $scope);
            newScope = angular.merge(newScope);
            $("#" + id).remove();

            var html = `<qscomp id='${id}' surveyquestionchoiceid='${value}' parentid='${surveyQuestionTableId}'></qscomp>`;
            $("#" + parentId).append($compile(html)(newScope));

            //store answer
            $rootScope.ansStore[`q_${surveyQuestionTableId}_${ctrl.parentid}`] = value;
        }

        $scope.TextChange = (value, surveyQuestionTableId) => {
            $rootScope.ansStore[`q_${surveyQuestionTableId}_${ctrl.parentid}_t`] = value;
        }

        $scope.getChoicesByQsId = (questionId) => {
            var str = and.GetChoicesById(questionId);
            return JSON.parse(and.GetChoicesById(questionId));
        }

        $scope.Qstype5Change = (surveyQuestionTableId, r, AnswerQsType5) => {
            $rootScope.ansStore[`q_${surveyQuestionTableId}_${ctrl.parentid}`] = AnswerQsType5;
        }

        $scope.RateClick = (qsid) => {
            $rootScope.ansStore[`q_${qsid}_${ctrl.parentid}`] = ctrl.rate;
        }

        $scope.QsType7Change = (surveyQuestionTableId, r, AnswerQsType7) => {
            $rootScope.ansStore[`q_${surveyQuestionTableId}_${ctrl.parentid}_t`] = AnswerQsType7;
        }
    }
});





var testCtrl = app.controller("tst", function ($scope, $rootScope) {
    $scope.isGeneral = true
    $scope.Visible = [true, false, false, false]

    this.$onInit = function () {
        $scope.isGeneral = and.isGeneral();
        if ($scope.isGeneral) {
            $scope.SurveyList = JSON.parse(and.GetSurveyList(1))
            $scope.SetVisible(2)
            $scope.setHeading("Survey List")
        }
        else {
            $scope.SetVisible(0)
            $scope.ProjectList = JSON.parse(and.GetProjectList())
            $scope.setHeading("Project List")
        }
    }

    $scope.SetVisible = (index: number) => {
        $scope.Visible.forEach((v, i, a) => a[i] = false)
        $scope.Visible[index] = true
        $scope.currentVisibleIndex = index
        switch (index) {
            case 0:
                $scope.setHeading("Audio Statement")
                if ($scope.isGeneral) and.closeActivity()
                break;
            case 1:
                $scope.setHeading("Project List")
                if ($scope.isGeneral) and.closeActivity()
                break;
            case 2:
                $scope.setHeading("Survey List")
                break;
            default:
                $scope.setHeading("")
                break;
        }
    }
    $scope.ProjectClick = (proj) => {
        $scope.selectedProject = proj;
        $scope.SurveyList = JSON.parse(and.GetSurveyList(proj.categoryId))
        $scope.checkProjectCompleted()
        if (proj.completed) return;
        $scope.SetVisible(2)
        $scope.setHeading("Survey List")
    }


    $scope.SurveyClick = (survey) => {
        if (survey.completed) return;
        $scope.SetVisible(3)
        and.setSurveyId(survey.id)
        $scope.selectedSurvey = survey;
        $scope.setHeading(survey.name)
    }

    $scope.OK = () => {
        var list = Object.keys($rootScope.ansStore).map(i => {
            var ids = i.split("_");
            var qsid = ids[1];
            var parentId = ids[2];
            var isText = ids.length > 3 ? 1 : 0;
            var value: string = $rootScope.ansStore[i];
            return { questionId: qsid, answer: value, parentId: parentId, isText: isText }
        });
        and.SaveAns(JSON.stringify(list));

        $scope.selectedSurvey.completed = true
        $scope.SetVisible(2)
        $scope.checkProjectCompleted()
    }

    $scope.checkProjectCompleted = () => {
        var anyNotCompleted = $scope.SurveyList.filter(i => !i.completed).length > 0
        if (!anyNotCompleted) $scope.selectedProject.completed = true
    }

    $scope.BackButtonClick = () => {
        if ($scope.currentVisibleIndex) {
            var i: number = $scope.currentVisibleIndex - 1
            if (i != -1) $scope.SetVisible(i)
            else
                and.closeActivity()
        } else {
            and.closeActivity()
        }
    }

    $scope.setHeading = (txt: string) => $("#pagehead").html(txt);

    $scope.ExitButtonClick = () => {
        and.closeActivity()
    }

    $scope.isRecording = false;
    $scope.isRecorded = false;
    $scope.BtnRecord = () => {
        (document.getElementById("imgrecord") as HTMLImageElement).src = "recording.svg"
        if (!$scope.isRecording) {
            and.StartRecording();
            $scope.isRecording = true;
        }
    }

    $scope.BtnStopRecord = () => {
        (document.getElementById("imgrecord") as HTMLImageElement).src = "record.svg"
        if ($scope.isRecording) {
            and.StopRecording();
            $scope.isRecording = false;
            $scope.isRecorded = true;
        }
    }

    $scope.BtnPlayRecord = () => {
        if ($scope.isRecorded) {
            and.StartPlaying();
        }
    }
    $scope.BtnSaveRecord = () => {
        and.SaveAudio();
        $scope.SetVisible(1)
    }
})
