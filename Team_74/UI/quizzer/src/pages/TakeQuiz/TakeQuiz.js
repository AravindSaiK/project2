import React, { Component } from 'react';
import './TakeQuiz.css';
import Result from '../../components/Result';
import quizQuestions from '../../api/quizQuestions';
import Quiz from '../../components/Quiz';
import Timer from "react-compound-timer";

class TakeQuiz extends Component {

    /**Constructor for the main TakeQuiz Class*/
    constructor(props) {
        super(props);
        this.state = {
            counter: 0,
            questionId: 1,
            questionSerial: 1,
            questionText: '',
            answerOptions: [],
            answer: '',
            answersCount: {
                nintendo: 0,
                microsoft: 0,
                sony: 0
            },
            result: ''
        };
        this.handleAnswerSelected = this.handleAnswerSelected.bind(this);
    }

    /**
     * componentWillMount life cycle event is invoked once,
     * both on the client and server, immediately before the initial rendering occurs*/

    componentWillMount() {
        const shuffledAnswerOptions = quizQuestions.map((question) => this.shuffleArray(question.options));
        this.setState({
            questionText: quizQuestions[0].questionText,
            answerOptions: shuffledAnswerOptions[0]
        });
        const params = new URLSearchParams(this.props.location.search);
        console.log(params.get('quizId'));
    }

    /**+
     * shuffleArray would randomise the order of questions*/
    shuffleArray(array) {
        var currentIndex = array.length, temporaryValue, randomIndex;
        
        while (0 !== currentIndex) {

            // Pick a remaining element...
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;

            // And swap it with the current element.
            temporaryValue = array[currentIndex];
            array[currentIndex] = array[randomIndex];
            array[randomIndex] = temporaryValue;
        }
        return array;
    };

    setUserAnswer(answer) {
        this.setState((state) => ({
            answersCount: {
                ...state.answersCount,
                [answer]: state.answersCount[answer] + 1
            },
            answer: answer
        }));

        console.log(answer);
    }

    setNextQuestion() {
        const counter = this.state.counter + 1;
        const questionSerial = this.state.questionSerial + 1;
        this.setState({
            counter: counter,
            questionId: quizQuestions[counter].id,
            questionSerial: questionSerial,
            questionText: quizQuestions[counter].questionText,
            answerOptions: quizQuestions[counter].options,
            answer: ''
        });
    }
    getResults() {
        const answersCount = this.state.answersCount;
        const answersCountKeys = Object.keys(answersCount);
        const answersCountValues = answersCountKeys.map((key) => answersCount[key]);
        const maxAnswerCount = Math.max.apply(null, answersCountValues);
        return answersCountKeys.filter((key) => answersCount[key] === maxAnswerCount);
    }

    setResults (result) {
        if (result.length === 1) {
            this.setState({ result: result[0] });
        } else {
            this.setState({ result: 'Undetermined' });
        }
    }

    /**+
     *handleAnswerSelected function performs two tasks: setting the answer and then setting the next question*/

    handleAnswerSelected(event) {
        this.setUserAnswer(event.currentTarget.value);
        if (this.state.questionSerial < quizQuestions.length) {
            setTimeout(() => this.setNextQuestion(), 300);
        } else {
            setTimeout(() => this.setResults(this.getResults()), 300);
        }
    }

    renderQuiz() {
        return (

            <Quiz
                answer={this.state.answer}
                answerOptions={this.state.answerOptions}
                questionId={this.state.questionId}
                questionSerial={this.state.questionSerial}
                question={this.state.questionText}
                questionTotal={quizQuestions.length}
                onAnswerSelected={this.handleAnswerSelected}
            />

        );
    }

    renderResult() {
        return <Result quizResult={this.state.result} />;
    }

    render() {
        return (
            <div className="TakeQuiz">
                <div className="TakeQuiz-header">
                    <h2>Demo Quiz</h2>
                    <Timer className="TakeQuiz-timer">
                        {/*<Timer.Days /> days*/}
                        {/*<Timer.Hours /> hours*/}
                        <Timer.Minutes /> minutes <span></span> <span></span>
                        <Timer.Seconds /> seconds
                        {/*<Timer.Milliseconds /> milliseconds*/}
                    </Timer>
                </div>
                {this.state.result ? this.renderResult() : this.renderQuiz()}
            </div>
        );
    }
}

export default TakeQuiz;
