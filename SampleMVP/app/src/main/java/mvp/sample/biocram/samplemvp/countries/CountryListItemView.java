package mvp.sample.biocram.samplemvp.countries;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.common.base.Preconditions;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.sample.biocram.samplemvp.R;
import mvp.sample.biocram.samplemvp.data.model.Country;

/**
 * Created by biocram on 2017-04-19.
 */

public class CountryListItemView extends LinearLayout {

    private Country mCountry;

    private GenericRequestBuilder mSvgRequestBuilder;

    @BindView(R.id.imageview_flag)
    ImageView mImageViewFlag;
    @BindView(R.id.textview_name)
    TextView mTextViewName;
    @BindView(R.id.textview_capital)
    TextView mTextViewCapital;
    @BindView(R.id.textview_nativeName)
    TextView mTextViewNativeName;

    public CountryListItemView(Context context) {
        super(context);
        if (!isInEditMode()) {
            init();
        }
    }

    public CountryListItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init();
        }
    }

    public CountryListItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            init();
        }
    }

    private void init() {
        inflate(getContext(), R.layout.country_list_item, this);

        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(HORIZONTAL);

        ButterKnife.bind(this);
    }

    void setCountry(@NonNull Country country) {
        Preconditions.checkNotNull(country, "Country can't be null!");
        mCountry = country;
        initUI();
    }

    private void initUI() {
        mTextViewName.setText(mCountry.name);
        mTextViewCapital.setText(String.format(getContext().getString(R.string.capital), mCountry.capital));
        mTextViewNativeName.setText(String.format(getContext().getString(R.string.native_name), mCountry.nativeName));
        mSvgRequestBuilder
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                // SVG cannot be serialized so it's not worth to cache it
                .load(Uri.parse(mCountry.flag))
                .into(mImageViewFlag);
    }

    public void setSvgRequestBuilder(GenericRequestBuilder svgRequestBuilder) {
        mSvgRequestBuilder = svgRequestBuilder;
    }
}
